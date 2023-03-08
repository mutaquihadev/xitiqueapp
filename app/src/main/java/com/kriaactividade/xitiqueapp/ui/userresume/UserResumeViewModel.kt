package com.kriaactividade.xitiqueapp.ui.userresume

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriaactividade.xitiqueapp.*
import com.kriaactividade.xitiqueapp.data.repository.UserListRepository
import com.kriaactividade.xitiqueapp.entity.BalanceTotal
import com.kriaactividade.xitiqueapp.entity.UserEntity
import com.kriaactividade.xitiqueapp.helpers.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val VALUE_DEFAULT = 300

data class UserResumeUiData(
    val balanceTotal: BalanceTotal = BalanceTotal(),
    val listUserEntity: List<UserEntity> = emptyList(),
    val listNext: List<UserEntity> = emptyList(),
    val isClicked:Boolean = false
)

sealed interface UserResumeUiState {
    object Loading:UserResumeUiState
    object Error: UserResumeUiState
    data class Success(val uiData: UserResumeUiData):UserResumeUiState
}

@HiltViewModel
class UserResumeViewModel @Inject constructor(private val repository: UserListRepository) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UserResumeUiState> =
        MutableStateFlow(UserResumeUiState.Loading)

    val uiState = _uiState.asStateFlow()
    private val today = Calendar.getInstance().time

    init {
        viewModelScope.launch {
            repository.getUserList().collect(::handleGetUserList)
        }
    }

    private fun handleGetUserList(state: DataState<List<UserEntity>>) {
        when (state) {
            is DataState.Data -> {


                val currentValue =
                    state.data.filter { it.birthday.isSameMonth(today) }.size * VALUE_DEFAULT
                val nextValue =
                    state.data.filter { it.birthday.isNextMonth(today) }.size * VALUE_DEFAULT
                val balanceForMonth =
                    state.data.filter { it.birthday.isBalanceInMonth(today) }.size * VALUE_DEFAULT
                val totalOfYear = state.data.size * VALUE_DEFAULT

                val balance = BalanceTotal(
                    currentBalance = "R$ $currentValue",
                    nextBalance = "R$ $nextValue",
                    balanceForYear = "R$ $totalOfYear",
                    percentForTotal = totalOfYear,
                    percentCurrent = balanceForMonth,
                    percent = balanceForMonth / 100
                )

               val users = state.data
               val group = users.groupBy {
                   it.birthday.groupMonths()
               }

                val listUserNextMonth = mutableListOf<UserEntity>()
                val listUserSameMonth = mutableListOf<UserEntity>()
                group.forEach {
                    it.value.forEach {user ->
                        if(user.birthday.isSameMonth(today)){
                            listUserSameMonth.add(UserEntity(fullname = user.fullname, birthday = user.birthday, balance = user.balance))
                        }
                        if (user.birthday.isNextMonth(today)){
                            listUserNextMonth.add(UserEntity(fullname = user.fullname, birthday = user.birthday, balance = user.balance))
                        }
                    }
                }

                _uiState.value = UserResumeUiState.Success(uiData = UserResumeUiData(balanceTotal = balance, listUserSameMonth, listUserNextMonth))
            }
            else -> {}
        }
    }

}
package com.kriaactividade.xitiqueapp.ui.userresume

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriaactividade.xitiqueapp.data.repository.UserListRepository
import com.kriaactividade.xitiqueapp.entity.BalanceTotal
import com.kriaactividade.xitiqueapp.entity.UserEntity
import com.kriaactividade.xitiqueapp.helpers.DataState
import com.kriaactividade.xitiqueapp.isBalanceInMonth
import com.kriaactividade.xitiqueapp.isNextMonth
import com.kriaactividade.xitiqueapp.isSameMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val VALUE_DEFAULT = 300

@HiltViewModel
class UserResumeViewModel @Inject constructor(private val repository: UserListRepository) :
    ViewModel() {

    private val _uiState: MutableStateFlow<List<BalanceTotal>> =
        MutableStateFlow(emptyList())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getUserList().collect(::handleGetUserList)
        }
    }

    private fun handleGetUserList(state: DataState<List<UserEntity>>) {
        when (state) {
            is DataState.Data -> {
                val listBalance = mutableListOf<BalanceTotal>()
                val today = Calendar.getInstance().time.time

                var valueCurrentEvent = 0
                var valueNextEvent = 0
                var valueForYear = 0
                var valueTotal = 0
                var count = 0
                var countForNextEvent = 0
                var countForBalanceInMonth = 0

                state.data.forEach { user ->
                    valueForYear = state.data.size * VALUE_DEFAULT
                    if (user.birthday.isSameMonth(today)) {
                        count++
                        valueCurrentEvent = count * VALUE_DEFAULT
                    }
                    if (user.birthday.isNextMonth(today)) {
                        countForNextEvent ++
                        valueNextEvent = countForNextEvent * VALUE_DEFAULT
                    }
                    if (user.birthday.isBalanceInMonth(today)) {
                        countForBalanceInMonth ++
                        valueTotal = countForBalanceInMonth * VALUE_DEFAULT
                    }

                }

                listBalance.add(
                    BalanceTotal(
                        currentBalance = "R$ $valueCurrentEvent",
                        nextBalance = "R$ $valueNextEvent",
                        balanceForYear = "R$ $valueForYear",
                        percentForTotal = valueForYear,
                        percentCurrent = valueTotal,
                        percent = valueTotal /100
                    )
                )
                _uiState.value = listBalance
            }
            else -> {}
        }
    }
}
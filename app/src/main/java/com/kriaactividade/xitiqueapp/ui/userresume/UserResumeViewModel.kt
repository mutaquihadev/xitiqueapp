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
                val today = Calendar.getInstance().time

                val currentValue = state.data.filter { it.birthday.isSameMonth(today) }.size * VALUE_DEFAULT
                val nextValue = state.data.filter { it.birthday.isNextMonth(today) }.size * VALUE_DEFAULT
                val balanceForMonth = state.data.filter { it.birthday.isBalanceInMonth(today) }.size * VALUE_DEFAULT
                val totalOfYear = state.data.size * VALUE_DEFAULT

                listBalance.add(
                    BalanceTotal(
                        currentBalance = "R$ $currentValue",
                        nextBalance = "R$ $nextValue",
                        balanceForYear = "R$ $totalOfYear",
                        percentForTotal = balanceForMonth,
                        percentCurrent = balanceForMonth,
                        percent = balanceForMonth /100
                    )
                )
                _uiState.value = listBalance
            }
            else -> {}
        }
    }
}
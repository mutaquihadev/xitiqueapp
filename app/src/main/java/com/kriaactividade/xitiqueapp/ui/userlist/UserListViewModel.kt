package com.kriaactividade.xitiqueapp.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriaactividade.xitiqueapp.data.repository.UserListRepository
import com.kriaactividade.xitiqueapp.entity.UserEntity
import com.kriaactividade.xitiqueapp.entity.UserForAdapter
import com.kriaactividade.xitiqueapp.groupMonths
import com.kriaactividade.xitiqueapp.helpers.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val repository: UserListRepository) :
    ViewModel() {

    private val _uiState: MutableStateFlow<List<UserForAdapter>> =
        MutableStateFlow(emptyList())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getUserList().collect(::handleGetUserList)
        }
    }

    private fun handleGetUserList(state: DataState<List<UserEntity>>) {
        when (state) {
            is DataState.Loading -> {

            }
            is DataState.Data -> {
                val users: List<UserEntity> = state.data
                val groupUser =  users.groupBy {
                    it.birthday.groupMonths()
                }
                val listForAdapter = mutableListOf<UserForAdapter>()

                groupUser.forEach{
                    listForAdapter.add(UserForAdapter.Header(description = it.key))
                    it.value.forEach {user ->
                        listForAdapter.add(UserForAdapter.Item(fullname = user.fullname, balance = user.balance, birthday = user.birthday, id = 0))
                    }
                }
                _uiState.value = listForAdapter
            }
            else -> {}
        }
    }

}
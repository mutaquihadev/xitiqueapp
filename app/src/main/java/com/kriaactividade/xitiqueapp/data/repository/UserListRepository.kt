package com.kriaactividade.xitiqueapp.data.repository

import com.kriaactividade.xitiqueapp.entity.UserEntity
import com.kriaactividade.xitiqueapp.helpers.DataState
import kotlinx.coroutines.flow.Flow


interface UserListRepository {

    fun getUserList() : Flow<DataState<List<UserEntity>>>
}
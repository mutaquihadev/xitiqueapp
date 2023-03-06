package com.kriaactividade.xitiqueapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kriaactividade.xitiqueapp.dto.UserDTO
import com.kriaactividade.xitiqueapp.entity.UserEntity
import com.kriaactividade.xitiqueapp.helpers.DataState
import com.kriaactividade.xitiqueapp.helpers.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserListRepositoryImp @Inject constructor(private val database: FirebaseFirestore) :
    UserListRepository {
    override fun getUserList(): Flow<DataState<List<UserEntity>>> = flow {
        emit(DataState.Loading(loadingState = LoadingState.Loading))
        val snapshot = database.collection("group-members-balance").get().await()
        val userDTOs = snapshot.toObjects(UserDTO::class.java)
        val userEntity = userDTOs.map { user ->
            UserEntity(
                fullname = user.fullname,
                balance = user.balance,
                birthday = user.birthday
            )
        }
        val userListSorted = userEntity.sortedBy {
            it.birthday
        }
        emit(DataState.Data(data = userListSorted))
    }

}
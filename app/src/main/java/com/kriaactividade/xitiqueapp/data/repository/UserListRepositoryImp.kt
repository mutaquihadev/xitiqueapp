package com.kriaactividade.xitiqueapp.data.repository

import androidx.compose.runtime.snapshotFlow
import com.google.firebase.firestore.FirebaseFirestore
import com.kriaactividade.xitiqueapp.dto.UserDTO
import com.kriaactividade.xitiqueapp.entity.UserEntity
import com.kriaactividade.xitiqueapp.helpers.DataState
import com.kriaactividade.xitiqueapp.helpers.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class UserListRepositoryImp @Inject constructor(private val database: FirebaseFirestore) :
    UserListRepository {
    override fun getUserList(): Flow<DataState<List<UserEntity>>> = callbackFlow {
        trySend(DataState.Loading(loadingState = LoadingState.Loading))
        val ref =
            database.collection("group-members-balance").addSnapshotListener { snapshot, error ->
                if (error != null) {
                    cancel()
                    return@addSnapshotListener
                } else if (snapshot != null) {
                    val userDTOs = snapshot.toObjects(UserDTO::class.java)
                    val userEntity = userDTOs.map { user ->
                        UserEntity(
                            fullname = user.fullname,
                            balance = user.balance,
                            birthday = Date(user.birthday)
                        )
                    }
                    val userListSorted = userEntity.sortedBy {
                        it.birthday
                    }
                    trySend(DataState.Data(data = userListSorted))
                }
            }
        awaitClose {
            ref.remove()
        }
    }
}
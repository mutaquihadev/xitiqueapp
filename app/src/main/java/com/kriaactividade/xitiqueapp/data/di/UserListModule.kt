package com.kriaactividade.xitiqueapp.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kriaactividade.xitiqueapp.data.repository.UserListRepository
import com.kriaactividade.xitiqueapp.data.repository.UserListRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UserListModule {
    @Provides
    fun providesUserListRepository(
        database: FirebaseFirestore
    ): UserListRepository {
        return UserListRepositoryImp(database)
    }
}
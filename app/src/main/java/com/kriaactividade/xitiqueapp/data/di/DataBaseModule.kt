package com.kriaactividade.xitiqueapp.data.di


import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Provides
    fun providesRealDataBaseInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}
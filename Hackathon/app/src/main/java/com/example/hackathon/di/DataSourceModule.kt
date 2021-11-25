package com.example.hackathon.di

import com.example.hackathon.data.api.*
import com.example.hackathon.data.datasource.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAccountDataSource(authRemote: AccountApi): AccountDataSource {
        return AccountDataSource(authRemote)
    }

    @Provides
    @Singleton
    fun provideFileDataSource(authRemote: FileApi): FileDataSource {
        return FileDataSource(authRemote)
    }

    @Provides
    @Singleton
    fun provideOwnerDataSource(authRemote: OwnerStoreApi): OwnerStoreDataSource {
        return OwnerStoreDataSource(authRemote)
    }

    @Provides
    @Singleton
    fun provideReviewDataSource(authRemote: ReviewApi): ReviewDataSource {
        return ReviewDataSource(authRemote)
    }

    @Provides
    @Singleton
    fun provideStoreDataSource(authRemote: StoreApi): StoreDataSource {
        return StoreDataSource(authRemote)
    }

    @Provides
    @Singleton
    fun provideUserDataSource(authRemote: UserApi): UserDataSource {
        return UserDataSource(authRemote)
    }
}
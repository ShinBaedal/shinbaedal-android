package com.example.hackathon.di

import com.example.hackathon.data.datasource.*
import com.example.hackathon.data.repository.*
import com.example.hackathon.data.repository.impl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepository(dataSource: AccountDataSource): LoginRepository {
        return LoginRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideFileRepository(dataSource: FileDataSource): FileRepository {
        return FileRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideMenuRepository(dataSource: MenuDataSource): MenuRepository {
        return MenuRepositoryImpl(dataSource)
    }
    @Provides
    @Singleton
    fun provideOwnerStoreRepository(dataSource: OwnerStoreDataSource): OwnerStoreRepository {
        return OwnerStoreRepositoryImpl(dataSource)
    }
    @Provides
    @Singleton
    fun provideReviewRepository(dataSource: ReviewDataSource): ReviewRepository {
        return ReviewRepositoryImpl(dataSource)
    }
    @Provides
    @Singleton
    fun provideSignUpRepository(dataSource: AccountDataSource): SignupRepository {
        return SignUpRepositoryImpl(dataSource)
    }
    @Provides
    @Singleton
    fun provideStoreRRepository(dataSource: StoreDataSource): StoreRepository {
        return StoreRepositoryImpl(dataSource)
    }
    @Provides
    @Singleton
    fun provideUserRepository(dataSource: UserDataSource): UserRepository {
        return UserRepositoryImpl(dataSource)
    }
    @Provides
    @Singleton
    fun provideOrderRepository(dataSource: OrderDataSource): OrderRepository {
        return OrderRepositoryImpl(dataSource)
    }


}
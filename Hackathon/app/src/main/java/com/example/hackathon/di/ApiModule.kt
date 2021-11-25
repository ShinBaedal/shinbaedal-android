package com.example.hackathon.di

import com.example.hackathon.data.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "http://49.50.172.207:3000"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAccountApi(retrofit: Retrofit): AccountApi {
        return (retrofit.create(AccountApi::class.java))
    }
    @Provides
    @Singleton
    fun provideFileApi(retrofit: Retrofit): FileApi {
        return (retrofit.create(FileApi::class.java))
    }
    @Provides
    @Singleton
    fun provideMenuApi(retrofit: Retrofit): MenuApi {
        return (retrofit.create(MenuApi::class.java))
    }
    @Provides
    @Singleton
    fun provideOrderApi(retrofit: Retrofit): OrderApi {
        return (retrofit.create(OrderApi::class.java))
    }
    @Provides
    @Singleton
    fun provideOwnerStoreApi(retrofit: Retrofit): OwnerStoreApi {
        return (retrofit.create(OwnerStoreApi::class.java))
    }
    @Provides
    @Singleton
    fun provideReviewApi(retrofit: Retrofit): ReviewApi {
        return (retrofit.create(ReviewApi::class.java))
    }
    @Provides
    @Singleton
    fun provideStoreApi(retrofit: Retrofit): StoreApi {
        return (retrofit.create(StoreApi::class.java))
    }
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return (retrofit.create(UserApi::class.java))
    }


}
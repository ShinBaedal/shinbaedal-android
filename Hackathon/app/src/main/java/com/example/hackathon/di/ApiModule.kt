package com.example.hackathon.di

import com.example.hackathon.base.AuthInterceptor
import com.example.hackathon.data.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "http://49.50.172.207:3000/"
    private const val SUB_URL = "http://172.20.10.2:3000/"

    @Provides
    @Singleton
    fun provideInterceptor() = AuthInterceptor()

    @Provides
    @Singleton
    fun provideClient(interceptor: AuthInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(SUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
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
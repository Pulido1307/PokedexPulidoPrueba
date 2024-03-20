package com.antonio.pulido.pokedexpulido.di

import com.antonio.pulido.web.ApiConstants
import com.antonio.pulido.web.ApiService
import com.antonio.pulido.web.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideRetrofit(): ApiService {
        RetrofitInstance.retrofitInstance = Retrofit.Builder()
            .baseUrl(ApiConstants.serverPath)
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(provideHttpClient())
            .build()
            .create(ApiService::class.java)

        if(RetrofitInstance.retrofitInstance == null){}
        return RetrofitInstance.retrofitInstance!!
    }


    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun gson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }
}
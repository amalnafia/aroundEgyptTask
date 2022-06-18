package com.example.aroundegypttask.data

import com.example.aroundegypttask.data.remote.RemoteDataSource
import com.example.aroundegypttask.util.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {


    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
                client.addInterceptor(Interceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                    chain.proceed(requestBuilder.build())
                })
                client.connectTimeout(120, TimeUnit.SECONDS)
                client.readTimeout(120, TimeUnit.SECONDS)
            }.build()
        )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    fun <T> provideApiService(cls: Class<T>): T {
        return provideRetrofit().create(cls)
    }

}
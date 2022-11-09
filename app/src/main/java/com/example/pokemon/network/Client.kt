package com.example.pokemon.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.viewbinding.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Client {
    fun create(): PokemonApi {
        val retrofit = Retrofit.Builder()
            .client(createClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://pokeapi.co")
            .build()

        return retrofit.create(PokemonApi::class.java)
    }

    private fun createClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            addLoggingInterceptor(builder)
        }

        return builder.build()
    }

    private fun addLoggingInterceptor(httpClientBuilder: OkHttpClient.Builder) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(loggingInterceptor)
    }
}
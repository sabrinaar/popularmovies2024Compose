package com.tmdb.popularmoviescompose.data.api

import com.tmdb.popularmoviescompose.util.BASE_URL
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
object Retrofit {


        @Provides
        @Singleton
        fun provideMoviesApi(): MoviesApi {
            // Crear el cliente OkHttp con el Interceptor
            val client = OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor())  // Agregar el interceptor que añade el api_key
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)  // Establecer el cliente OkHttp con el interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
    }

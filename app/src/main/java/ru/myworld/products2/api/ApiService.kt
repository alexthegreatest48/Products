package ru.myworld.products2.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.myworld.products2.dto.Samurai

private const val BASE_URL = "https://dummyjson.com/"

private val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val okhttp = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okhttp)
    .build()

interface ApiService {
    @GET("products")
    suspend fun getPagingAll(@Query("skip=") pager: Int, @Query("limit=") limit: Int): Response<Samurai>
}

object ProductsApi {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
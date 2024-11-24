package com.example.sgma.data.datasource.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(
    private val url : String,
    public val secretKey : String,
) {
    val httpClient : OkHttpClient
    val retrofit : Retrofit

    init {
        httpClient = OkHttpClient
            .Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request();

                    val request = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("User-Agent", System.getProperty("http.agent"))
                        .addHeader("Host", "localhost:8080")
                        .method(original.method, original.body)
                        .build();

                    return chain.proceed(request);
                }

            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
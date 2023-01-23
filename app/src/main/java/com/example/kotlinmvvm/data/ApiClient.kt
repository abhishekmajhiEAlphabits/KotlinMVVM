package com.example.kotlinmvvm.data

import retrofit2.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object ApiClient {

    //https://obscure-earth-55790.herokuapp.com/api/museums
    private const val API_BASE_URL = "https://reqres.in/"

    private var servicesApiInterface: ServicesApiInterface? = null

    fun build():ServicesApiInterface{
       var retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java
        )

        return servicesApiInterface as ServicesApiInterface
    }



    interface ServicesApiInterface {
        @GET("/api/users?page=2")
        fun museums(): Call<MuseumResponse>
    }
}

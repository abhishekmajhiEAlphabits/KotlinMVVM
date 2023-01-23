package com.example.kotlinmvvm.model

import java.io.Serializable

data class Museum(
    val id: Int,
    val email:String,
    val first_name: String,
    val last_name:String,
    val avatar: String
) : Serializable
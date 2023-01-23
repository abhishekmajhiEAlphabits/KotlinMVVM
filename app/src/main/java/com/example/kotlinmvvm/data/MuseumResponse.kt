package com.example.kotlinmvvm.data

import com.example.kotlinmvvm.model.Museum

data class MuseumResponse(
    val page: Int?,
    val per_page: Int?,
    val total: Int?,
    val total_pages: Int?,
    val data: List<Museum>?
)

/*
{
    fun isSuccess(): Boolean = (status == 200)
}
 */
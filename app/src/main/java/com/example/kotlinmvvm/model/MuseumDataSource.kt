package com.example.kotlinmvvm.model

import com.example.kotlinmvvm.data.OperationCallback

interface MuseumDataSource {

    fun retrieveMuseums(callback: OperationCallback<Museum>)
    fun cancel()
}
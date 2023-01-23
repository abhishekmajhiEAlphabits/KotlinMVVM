package com.example.kotlinmvvm.model

import com.example.kotlinmvvm.data.OperationCallback

class MuseumRepository(private val museumDataSource: MuseumDataSource) {

    fun fetchMuseums(callback: OperationCallback<Museum>) {
        museumDataSource.retrieveMuseums(callback)
    }

    fun cancel() {
        museumDataSource.cancel()
    }
}
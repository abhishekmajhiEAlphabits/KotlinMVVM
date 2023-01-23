package com.example.kotlinmvvm.data

import retrofit2.Call
import com.example.kotlinmvvm.model.Museum
import com.example.kotlinmvvm.model.MuseumDataSource
import retrofit2.Callback
import retrofit2.Response


class MuseumRemoteDataSource(apiClient: ApiClient):MuseumDataSource {

    private var call: Call<MuseumResponse>? = null
    private val service = apiClient.build()

    override fun retrieveMuseums(callback: OperationCallback<Museum>) {

        call = service?.museums()
        call?.enqueue(object:Callback<MuseumResponse>{
            override fun onResponse(
                call: Call<MuseumResponse>,
                response: Response<MuseumResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it.data)
                    } else {
                        callback.onError("Error")
                    }
                }
            }

            override fun onFailure(call: Call<MuseumResponse>, t: Throwable) {
                callback.onError(t.message)
            }
        })

    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}
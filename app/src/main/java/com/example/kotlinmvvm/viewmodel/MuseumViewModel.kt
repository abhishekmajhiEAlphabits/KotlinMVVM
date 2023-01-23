package com.example.kotlinmvvm.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvm.data.OperationCallback
import com.example.kotlinmvvm.model.Museum
import com.example.kotlinmvvm.model.MuseumRepository

class MuseumViewModel(private val repository: MuseumRepository):ViewModel() {

    private val _museums = MutableLiveData<List<Museum>>()
    val museums: LiveData<List<Museum>> = _museums


    fun loadMuseums(){
        repository.fetchMuseums(object:OperationCallback<Museum>{
            override fun onSuccess(data: List<Museum>?) {
                if (data.isNullOrEmpty()) {
                    //_isEmptyList.value = true

                } else {
                    _museums.value = data
                }
            }

            override fun onError(error: String?) {
                Log.d("TAG",error.toString())
            }
        })
    }
}
package com.example.kotlinmvvm.di

import com.example.kotlinmvvm.data.ApiClient
import com.example.kotlinmvvm.data.MuseumRemoteDataSource
import com.example.kotlinmvvm.model.MuseumDataSource
import com.example.kotlinmvvm.model.MuseumRepository
import com.example.kotlinmvvm.viewmodel.ViewModelFactory


object Injection {

    private var museumDataSource: MuseumDataSource? = null
    private var museumRepository: MuseumRepository? = null
    private var museumViewModelFactory: ViewModelFactory? = null

    private fun createMuseumDataSource(): MuseumDataSource {
        val dataSource = MuseumRemoteDataSource(ApiClient)
        museumDataSource = dataSource
        return dataSource
    }

    private fun createMuseumRepository(): MuseumRepository {
        val repository = MuseumRepository(provideDataSource())
        museumRepository = repository
        return repository
    }

    private fun createFactory(): ViewModelFactory {
        val factory = ViewModelFactory(providerRepository())
        museumViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = museumDataSource ?: createMuseumDataSource()
    private fun providerRepository() = museumRepository ?: createMuseumRepository()

    fun provideViewModelFactory() = museumViewModelFactory ?: createFactory()

    fun destroy() {
        museumDataSource = null
        museumRepository = null
        museumViewModelFactory = null
    }
}
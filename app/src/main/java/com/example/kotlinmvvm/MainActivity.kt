package com.example.kotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvm.data.ApiClient
import com.example.kotlinmvvm.data.MuseumRemoteDataSource
import com.example.kotlinmvvm.di.Injection
import com.example.kotlinmvvm.model.Museum
import com.example.kotlinmvvm.model.MuseumDataSource
import com.example.kotlinmvvm.model.MuseumRepository
import com.example.kotlinmvvm.viewmodel.MuseumViewModel
import com.example.kotlinmvvm.viewmodel.ViewModelFactory
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvm.databinding.ActivityMainBinding
import com.example.kotlinmvvm.view.MuseumAdapter

class MainActivity : AppCompatActivity() {

    private val TAG = "CONSOLE"

    private lateinit var factory: ViewModelFactory
    private lateinit var viewModel: MuseumViewModel
    private lateinit var adapter: MuseumAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val api = ApiClient
        val dataSource = MuseumRemoteDataSource(api)
        val repository = MuseumRepository(dataSource)
        factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MuseumViewModel::class.java)

        adapter = MuseumAdapter(viewModel.museums.value ?: emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.museums.observe(this, renderMuseums)
        //viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        //viewModel.onMessageError.observe(this, onMessageErrorObserver)
        //viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    private val renderMuseums = Observer<List<Museum>> {
        Log.d(TAG, "data updated $it")
        adapter.update(it)
    }


    override fun onResume() {
        super.onResume()
        viewModel.loadMuseums()
    }

    override fun onDestroy() {
        super.onDestroy()
        Injection.destroy()
    }


}
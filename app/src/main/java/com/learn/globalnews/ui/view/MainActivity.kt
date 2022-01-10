package com.learn.globalnews.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.learn.globalnews.*
import com.learn.globalnews.data.repository.MainRepository
import com.learn.globalnews.ui.viewmodel.MyViewModelFactory
import com.learn.globalnews.data.api.RetrofitService
import com.learn.globalnews.databinding.ActivityMainBinding
import com.learn.globalnews.ui.adapter.MainAdapter
import com.learn.globalnews.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService)))[MainViewModel::class.java]
        binding.newsListRecyclerViw.adapter = adapter
        viewModel.newsList.observe(this, Observer {
            adapter.setNewsList(it)
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getAllNewsWithSources()
        viewModel.getAllPopularNewsWithSources()
    }
}
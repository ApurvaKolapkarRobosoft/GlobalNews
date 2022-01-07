package com.learn.globalnews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.globalnews.data.model.NewsModel
import com.learn.globalnews.data.model.PreNewsModel
import com.learn.globalnews.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopNewsFragmentViewModel constructor(private val repository: MainRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val news = MutableLiveData<NewsModel>()

    fun getAllPopularNewsWithSources() {
        val response = repository.executePopularNewsApi()
        response?.enqueue(object : Callback<PreNewsModel> {
            override fun onResponse(call: Call<PreNewsModel>, response: Response<PreNewsModel>) {
                val resBody = response.body()
                news.postValue(resBody?.articles?.get(0))
            }

            override fun onFailure(call: Call<PreNewsModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
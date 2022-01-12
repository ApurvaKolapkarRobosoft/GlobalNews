package com.learn.globalnews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.globalnews.data.model.NewsModel
import com.learn.globalnews.data.model.PreNewsModel
import com.learn.globalnews.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsListViewModel(private val repository: MainRepository, get: Any, get1: Any) : ViewModel() {
    val newsList = MutableLiveData<List<NewsModel>>()
    val errorMessage = MutableLiveData<String>()
    private val detailsNewsUrl = MutableLiveData<NewsModel>()

    fun getAllNewsWithSources() {
        val response = repository.executePreNewsApi(0)
        response?.enqueue(object : Callback<PreNewsModel> {
            override fun onResponse(call: Call<PreNewsModel>, response: Response<PreNewsModel>) {
                val resBody = response.body()
                newsList.postValue(resBody?.articles)
                print(resBody)
            }

            override fun onFailure(call: Call<PreNewsModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
    fun setDetailsUrl(newsModel: NewsModel) {
        detailsNewsUrl.value = newsModel
    }

    fun getDetailsUrl(): MutableLiveData<NewsModel> {
        return detailsNewsUrl
    }

}
package com.learn.globalnews.ui.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.globalnews.data.repository.MainRepository
import com.learn.globalnews.data.model.NewsModel
import com.learn.globalnews.data.model.PreNewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
Using a repository pattern to handle the data from API.
In the repository class, we need to pass the retrofit service instance to perform the network call.
We don’t need to handle the response here in the repository. That will be part of the ViewModel.

ViewModel class having the business logic and API call implementations.
In the ViewModel constructor, we need to pass the data repository to handle the data.

Since LiveData respects Android Lifecycle, this means it will not invoke its observer callback unless
activity or fragment is received onStart() but did not accept onStop() Adding to this,
LiveData will also automatically remove the observer when its host receives onDestroy().
 */
class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val newsList = MutableLiveData<List<NewsModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllNewsWithSources() {
        val response = repository.executePreNewsApi(0)
        response?.enqueue(object :Callback<PreNewsModel>
        {
            override fun onResponse(call: Call<PreNewsModel>, response: Response<PreNewsModel>) {
                val resBody=response.body()
                newsList.postValue(resBody?.articles)
                print(resBody)
            }

            override fun onFailure(call: Call<PreNewsModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getAllPopularNewsWithSources() {
        val response = repository.executePopularNewsApi()
        response?.enqueue(object :Callback<PreNewsModel>
        {
            override fun onResponse(call: Call<PreNewsModel>, response: Response<PreNewsModel>) {
                val resBody=response.body()
                print(resBody)
            }

            override fun onFailure(call: Call<PreNewsModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
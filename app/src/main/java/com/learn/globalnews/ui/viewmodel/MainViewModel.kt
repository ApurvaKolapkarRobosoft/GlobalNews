package com.learn.globalnews.ui.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.globalnews.data.model.MoviesModel
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
    val moviesList = MutableLiveData<List<MoviesModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<MoviesModel>> {
            override fun onResponse(call: Call<List<MoviesModel>>, response: Response<List<MoviesModel>>) {
                moviesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<MoviesModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getAllNewsWithSources() {
        val response = repository.executePreNewsApi(0)
        response?.enqueue(object :Callback<PreNewsModel>
        {
            override fun onResponse(call: Call<PreNewsModel>, response: Response<PreNewsModel>) {
                var resBody=response.body()
                newsList.postValue(resBody?.articles)
                print(resBody)
            }

            override fun onFailure(call: Call<PreNewsModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
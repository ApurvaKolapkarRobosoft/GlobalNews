package com.learn.globalnews.data.repository
import com.learn.globalnews.data.api.RetrofitService
import com.learn.globalnews.data.model.NewsModel
import com.learn.globalnews.data.model.PreNewsModel
import com.learn.globalnews.utils.Constant
import retrofit2.Call

//In the repository class, we need to pass the retrofit service instance to perform the network call.
class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
    fun executeNewsApi(index: Int): Call<List<NewsModel>>?
    {
        return retrofitService.fetchListNews(
            Constant.sources[index],
            Constant.API_KEY
        )
    }
    fun executePreNewsApi(index: Int): Call<PreNewsModel>?
    {
        return retrofitService.fetchPreListNews(
            Constant.sources[index],
            Constant.API_KEY
        )
    }
}
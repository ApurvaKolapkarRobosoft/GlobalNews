package com.learn.globalnews.data.api
import com.learn.globalnews.data.model.MoviesModel
import com.learn.globalnews.data.model.NewsModel
import com.learn.globalnews.data.model.PreNewsModel
import com.learn.globalnews.utils.Constant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

//Interface for api call definition
interface RetrofitService {

    @GET("movielist.json")
    fun getAllMovies() : Call<List<MoviesModel>>//base url of movies

    @GET(Constant.FetchNewsList)
    fun fetchListNews(
        @Query("source") source: String?,
        @Query("apiKey") apiKey: String?
    ): Call<List<NewsModel>>?

    @GET(Constant.FetchNewsList)
    fun fetchPreListNews(
        @Query("source") source: String?,
        @Query("apiKey") apiKey: String?
    ): Call<PreNewsModel>?

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
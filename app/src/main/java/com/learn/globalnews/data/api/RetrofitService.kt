package com.learn.globalnews.data.api

import com.learn.globalnews.data.model.PreNewsModel
import com.learn.globalnews.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Interface for api call definition
interface RetrofitService {

    @GET(Constant.FetchNewsListHeadLine)
    fun fetchPreListNews(
        @Query("sources") source: String?,
        @Query("apiKey") apiKey: String?
    ): Call<PreNewsModel>?

    @GET(Constant.FetchNewsListHeadLine)
    fun fetchPopularNews(
        @Query("country") country: String?,
        @Query("pageSize") pageSize: Int?,
        @Query("apiKey") apiKey: String?
    ): Call<PreNewsModel>?
}
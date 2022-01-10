package com.learn.globalnews.data.model

data class NewsModel(
    val source: Source,
    val author:String,
    val articles: String,
    val title: String,
    val description:String,
    val url:String,
    val urlToImage: String,
    val publishedAt:String)

data class Source(
    val id:String,
    val name: String)

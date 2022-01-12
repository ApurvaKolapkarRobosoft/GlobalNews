package com.learn.globalnews.ui.interfaces

import com.learn.globalnews.data.model.NewsModel

interface ListItemClick {
    fun onItemClicked(newsDataModel: NewsModel)
}
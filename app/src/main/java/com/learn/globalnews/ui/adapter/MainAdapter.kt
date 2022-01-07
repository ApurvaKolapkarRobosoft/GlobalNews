package com.learn.globalnews.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learn.globalnews.data.model.NewsModel
import com.learn.globalnews.databinding.AdapterNewsBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var news = mutableListOf<NewsModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setNewsList(news: List<NewsModel>) {
        this.news = news.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterNewsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val news = news[position]
        holder.binding.name.text = news.title
        holder.binding.descriptionText.text = news.description
        holder.binding.source.text = news.source.name
        Glide.with(holder.itemView.context).load(news.urlToImage).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return news.size
    }
}

class MainViewHolder(val binding: AdapterNewsBinding) : RecyclerView.ViewHolder(binding.root)

package com.learn.globalnews.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.learn.globalnews.R
import com.learn.globalnews.data.api.RetrofitService
import com.learn.globalnews.data.repository.MainRepository
import com.learn.globalnews.ui.adapter.MainAdapter
import com.learn.globalnews.ui.viewmodel.NewsListViewModel
import com.learn.globalnews.ui.viewmodel.NewsListViewModelFactory

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsListViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            NewsListViewModelFactory(MainRepository(retrofitService))
        )[NewsListViewModel::class.java]
        val newsListRecyclerViw = view.findViewById<RecyclerView>(R.id.newsListRecyclerViw)
        newsListRecyclerViw.adapter = adapter
        viewModel.newsList.observe(requireActivity(), Observer {
            adapter.setNewsList(it)
        })
        viewModel.errorMessage.observe(requireActivity(), Observer {
        })
        viewModel.getAllNewsWithSources()
    }
}
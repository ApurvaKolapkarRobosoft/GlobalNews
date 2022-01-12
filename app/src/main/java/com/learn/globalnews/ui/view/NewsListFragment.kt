package com.learn.globalnews.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.learn.globalnews.R
import com.learn.globalnews.ui.adapter.MainAdapter
import com.learn.globalnews.ui.viewmodel.NewsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment() {

    private val newsListViewModel: NewsListViewModel by viewModel()
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
        val newsListRecyclerViw = view.findViewById<RecyclerView>(R.id.newsListRecyclerViw)
        newsListRecyclerViw.adapter = adapter
        newsListViewModel.newsList.observe(requireActivity(), Observer {
            adapter.setNewsList(it)
        })
        newsListViewModel.errorMessage.observe(requireActivity(), Observer {
        })
        newsListViewModel.getAllNewsWithSources()
    }
}
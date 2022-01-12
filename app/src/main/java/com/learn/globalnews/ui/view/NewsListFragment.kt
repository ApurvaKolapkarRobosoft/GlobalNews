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
import com.learn.globalnews.ui.interfaces.ListItemClick
import com.learn.globalnews.ui.viewmodel.NewsListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.learn.globalnews.data.model.NewsModel


class NewsListFragment : Fragment(), ListItemClick {

    private val newsListViewModel: NewsListViewModel by sharedViewModel()
    private val adapter = MainAdapter(this)

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

    override fun onItemClicked(newsDataModel: NewsModel) {
        newsListViewModel.setDetailsUrl(newsDataModel)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentsContainer, NewsDetailsFragment())
            .addToBackStack(null)
            .commit()
    }

}
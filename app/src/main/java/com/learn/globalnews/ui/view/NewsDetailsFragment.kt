package com.learn.globalnews.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.learn.globalnews.R
import com.learn.globalnews.ui.viewmodel.NewsListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewsDetailsFragment : Fragment() {

    private val newsListViewModel: NewsListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webViewDetailNews = view.findViewById<WebView>(R.id.webviewDetailNews)
        newsListViewModel.getDetailsUrl().observe(requireActivity(), {
            val url = it.url.replace("http", "https")
            webViewDetailNews.loadUrl(url)
        })

    }

}
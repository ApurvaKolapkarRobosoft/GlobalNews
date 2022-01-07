package com.learn.globalnews.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.learn.globalnews.R
import com.learn.globalnews.data.api.RetrofitService
import com.learn.globalnews.data.repository.MainRepository
import com.learn.globalnews.ui.viewmodel.TopNewsFragmentViewModel
import com.learn.globalnews.ui.viewmodel.TopNewsViewModelFactory

class TopNewsFragment : Fragment() {

    private val retrofitService = RetrofitService.getInstance()
    private lateinit var viewModel: TopNewsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            TopNewsViewModelFactory(MainRepository(retrofitService))
        )[TopNewsFragmentViewModel::class.java]
        val newsImageViw = view.findViewById<ImageView>(R.id.topNewsImage)
        val topNewsname = view.findViewById<TextView>(R.id.topNewsname)
        val topNewsdescription = view.findViewById<TextView>(R.id.topNewsdescription)
        val textSource = view.findViewById<TextView>(R.id.textSource)
        viewModel.news.observe(requireActivity(), Observer {
            topNewsname.text = it.title
            topNewsdescription.text = it.description
            textSource.text = it.source.name
            Glide.with(newsImageViw.context).load(it.urlToImage).into(newsImageViw)
        })
        viewModel.errorMessage.observe(requireActivity(), Observer {
        })
        viewModel.getAllPopularNewsWithSources()
    }

}
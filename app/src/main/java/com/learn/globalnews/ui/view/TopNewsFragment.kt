package com.learn.globalnews.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.learn.globalnews.R
import com.learn.globalnews.ui.viewmodel.TopNewsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopNewsFragment : Fragment() {

    private val topNewsviewModel: TopNewsFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsImageViw = view.findViewById<ImageView>(R.id.topNewsImage)
        val topNewsname = view.findViewById<TextView>(R.id.topNewsname)
        val topNewsdescription = view.findViewById<TextView>(R.id.topNewsdescription)
        val textSource = view.findViewById<TextView>(R.id.textSource)
        topNewsviewModel.news.observe(requireActivity(), {
            topNewsname.text = it.title
            topNewsdescription.text = it.description
            textSource.text = it.source.name
            Glide.with(newsImageViw.context).load(it.urlToImage).into(newsImageViw)
        })
        topNewsviewModel.errorMessage.observe(requireActivity(), {
        })
        topNewsviewModel.getAllPopularNewsWithSources()
    }

}
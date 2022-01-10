package com.learn.globalnews.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.globalnews.R
import com.learn.globalnews.ui.viewmodel.PopularNewsFragmentViewModel

class PopularNewsFragment : Fragment() {

    companion object {
        fun newInstance() = PopularNewsFragment()
    }

    private lateinit var viewModel: PopularNewsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.popular_news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PopularNewsFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
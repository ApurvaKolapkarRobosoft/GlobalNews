package com.learn.globalnews.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learn.globalnews.*
import com.learn.globalnews.ui.viewmodel.NewsListViewModel
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsListViewModel: NewsListViewModel by viewModel()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()
        else
            super.onBackPressed()
    }
}
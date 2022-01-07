package com.learn.globalnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.globalnews.data.repository.MainRepository


/*
We can not create ViewModel on our own.
We need ViewModelProviders utility provided by Android to create ViewModels.
But ViewModelProviders can only instantiate ViewModels with no arg constructor.
So if I have a ViewModel with multiple arguments, then I need to use a Factory that I can pass
to ViewModelProviders to use when an instance of MyViewModel is required.
 */
class TopNewsViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TopNewsFragmentViewModel::class.java)) {
            TopNewsFragmentViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}
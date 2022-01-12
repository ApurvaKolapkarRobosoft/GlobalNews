package com.learn.globalnews.di

import com.learn.globalnews.data.api.RetrofitService
import com.learn.globalnews.data.repository.MainRepository
import com.learn.globalnews.ui.viewmodel.NewsListViewModel
import com.learn.globalnews.ui.viewmodel.TopNewsFragmentViewModel
import com.learn.globalnews.utils.Constant
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val newsListViewModelModule = module {
    viewModel {
        NewsListViewModel(get())
    }
}

val topNewsFragmentViewModelModule = module {
    viewModel {
        TopNewsFragmentViewModel(get())
    }
}

val repositoryModule = module {
    single {
        MainRepository(get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { provideRetrofit() }
}



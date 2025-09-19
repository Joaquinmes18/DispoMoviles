package com.calyrsoft.ucbp1.di

import com.calyrsoft.ucbp1.features.dollar.data.repository.DollarRepository
import com.calyrsoft.ucbp1.features.dollar.data.source.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import com.calyrsoft.ucbp1.features.dollar.domain.usecase.FetchDollarUseCase
import com.calyrsoft.ucbp1.features.dollar.presentation.DollarViewModel
import com.calyrsoft.ucbp1.features.github.data.api.GithubService
import com.calyrsoft.ucbp1.features.github.data.datasource.GithubRemoteDataSource
import com.calyrsoft.ucbp1.features.github.data.repository.GithubRepository
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository
import com.calyrsoft.ucbp1.features.github.domain.usecase.FindByNickNameUseCase
import com.calyrsoft.ucbp1.features.github.presentation.GithubViewModel
import com.calyrsoft.ucbp1.features.login.data.repository.LoginRepository
import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository
import com.calyrsoft.ucbp1.features.login.domain.usecase.LoginUseCase
import com.calyrsoft.ucbp1.features.login.domain.usecase.RecoverPasswordUseCase
import com.calyrsoft.ucbp1.features.login.presentation.LoginViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { RealTimeRemoteDataSource() }

// Repository
    single<IDollarRepository> { DollarRepository(get()) }

// Use case
    factory { FetchDollarUseCase(get()) }

// ViewModel
    viewModel { DollarViewModel(get()) }

    // GithubService
    single<GithubService> {
        get<Retrofit>().create(GithubService::class.java)
    }

    factory { FindByNickNameUseCase(get()) }
    viewModel { GithubViewModel(get()) }

    viewModel { LoginViewModel(get(), get()) }
    factory { LoginUseCase(get()) }
    single<ILoginRepository>{ LoginRepository() }
    factory { RecoverPasswordUseCase(get()) }
}
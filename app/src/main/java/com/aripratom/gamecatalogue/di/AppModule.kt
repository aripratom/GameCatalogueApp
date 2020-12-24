package com.aripratom.gamecatalogue.di

import com.aripratom.core.domain.usecase.GameInteractor
import com.aripratom.core.domain.usecase.GameUseCase
import com.aripratom.gamecatalogue.detail.GameDetailViewModel
import com.aripratom.gamecatalogue.home.HomeViewModel
import com.aripratom.gamecatalogue.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { GameDetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}
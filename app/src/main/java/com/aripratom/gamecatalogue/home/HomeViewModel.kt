package com.aripratom.gamecatalogue.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aripratom.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val popularGames = gameUseCase.getPopularGames().asLiveData()
}
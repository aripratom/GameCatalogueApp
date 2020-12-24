package com.aripratom.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aripratom.core.domain.usecase.GameUseCase

class FavoriteViewModel (gameUseCase: GameUseCase) : ViewModel() {
    val favoriteGame = gameUseCase.getFavoriteGame().asLiveData()
}
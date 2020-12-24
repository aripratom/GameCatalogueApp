package com.aripratom.gamecatalogue.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aripratom.core.domain.model.Game
import com.aripratom.core.domain.usecase.GameUseCase

class GameDetailViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    fun getGameDetail(id: String) = gameUseCase.getGameDetail(id).asLiveData()

    fun setFavoriteGame(game: Game, newState: Boolean) =
        gameUseCase.setFavoriteGame(game, newState)
}
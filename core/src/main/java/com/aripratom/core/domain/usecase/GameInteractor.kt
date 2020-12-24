package com.aripratom.core.domain.usecase

import com.aripratom.core.data.Resource
import com.aripratom.core.domain.model.Game
import com.aripratom.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {

    override fun getPopularGames() = gameRepository.getPopularGames()

    override fun getGameDetail(id: String): Flow<Resource<Game>> = gameRepository.getGameDetail(id)

    override fun getFavoriteGame() = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, state: Boolean) =
            gameRepository.setFavoriteGame(game, state)

    override fun searchGames(name: String): Flow<Resource<List<Game>>> = gameRepository.searchGames(name)
}
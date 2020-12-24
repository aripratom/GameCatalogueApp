package com.aripratom.core.domain.repository

import com.aripratom.core.data.Resource
import com.aripratom.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getPopularGames(): Flow<Resource<List<Game>>>

    fun getGameDetail(id: String): Flow<Resource<Game>>

    fun getFavoriteGame(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)

    fun searchGames(name: String): Flow<Resource<List<Game>>>
}
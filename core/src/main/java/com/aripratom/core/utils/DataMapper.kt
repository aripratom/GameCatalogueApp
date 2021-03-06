package com.aripratom.core.utils

import com.aripratom.core.data.source.local.entity.GameEntity
import com.aripratom.core.data.source.remote.response.GameResponse
import com.aripratom.core.domain.model.Game

object DataMapper {

    fun mapResponseToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingCount = it.ratingCount,
                description = it.description,
                website = it.website,
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingCount = it.ratingCount,
                isFavorite = it.isFavorite,
                description = it.description,
                website = it.website
            )

        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        id = input.id,
        name = input.name,
        released = input.released,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        ratingCount = input.ratingCount,
        description = input.description,
        website = input.website,
        isFavorite = input.isFavorite
    )

    fun mapEntityToDomain(input: GameEntity) = Game(
        id = input.id,
        name = input.name,
        released = input.released,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        ratingCount = input.ratingCount,
        description = input.description,
        website = input.website,
        isFavorite = input.isFavorite
    )

}
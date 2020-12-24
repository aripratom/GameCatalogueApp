package com.aripratom.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.aripratom.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game WHERE ratingCount > 1000")
    fun getPopularGames(): Flow<List<GameEntity>>

    @RawQuery(observedEntities = [GameEntity::class])
    fun searchGames(query: SupportSQLiteQuery): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE id = :id")
    fun getGameDetail(id: Int): Flow<GameEntity>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavoriteGame(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameList(game: List<GameEntity>)

    @RawQuery
    suspend fun updateGameData(query: SupportSQLiteQuery): GameEntity

    @Update
    fun updateFavoriteGame(gameEntity: GameEntity)
}
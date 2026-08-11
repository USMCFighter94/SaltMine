package dev.kolin.saltmine.db.game

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface GameDao {
    @Insert
    suspend fun insert(item: GameEntity): Long

    @Query("SELECT * FROM GameEntity WHERE id = :id")
    suspend fun find(id: Long): List<GameEntity>

    @Query("SELECT count(*) FROM GameEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM GameEntity")
    fun getAllAsFlow(): Flow<List<GameEntity>>
}

package dev.kolin.saltmine.db.game

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
public interface GameDao {
    @Insert
    public suspend fun insert(item: GameEntity): Long

    @Query("SELECT * FROM GameEntity WHERE id = :id")
    public suspend fun find(id: Long): List<GameEntity>

    @Query("SELECT count(*) FROM GameEntity")
    public suspend fun count(): Int

    @Query("SELECT * FROM GameEntity")
    public fun getAllAsFlow(): Flow<List<GameEntity>>
}

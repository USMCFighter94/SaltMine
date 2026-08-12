package dev.kolin.saltmine.setup

import dev.kolin.saltmine.db.game.GameDao
import dev.kolin.saltmine.db.game.GameEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeGameDao : GameDao {
    var nextKey = 0L
    val games = mutableMapOf<Long, GameEntity>()

    override suspend fun insert(item: GameEntity): Long {
        val key = nextKey++
        games[key] = item.copy(id = key)
        return key
    }

    override suspend fun find(id: Long): List<GameEntity> = listOfNotNull(games[id])

    override suspend fun count(): Int = games.size

    override fun getAllAsFlow(): Flow<List<GameEntity>> = flowOf(games.values.toList())
}

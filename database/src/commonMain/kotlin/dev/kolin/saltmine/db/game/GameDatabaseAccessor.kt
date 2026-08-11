package dev.kolin.saltmine.db.game

import dev.kolin.saltmine.core.domain.Format
import dev.kolin.saltmine.core.domain.Game
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Inject
public class GameDatabaseAccessor internal constructor(
    private val dao: GameDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    public suspend fun createNewGame(
        name: String,
        players: Int,
        format: Format,
    ): Long {
        val entity = GameEntity(
            name = name,
            players = players,
            format = format.toEntity(),
        )

        return withContext(dispatcher) { dao.insert(entity) }
    }

    public suspend fun getGame(id: Long): Game? = withContext(dispatcher) {
        dao.find(id)
            .firstOrNull()
            ?.toGame()
            ?.getOrNull()
    }

    public suspend fun count(): Int = withContext(dispatcher) { dao.count() }

    public fun observeAll(): Flow<List<Game>> = dao.getAllAsFlow()
        .map { entities ->
            entities.mapNotNull { entity ->
                entity.toGame()
                    .onFailure { println("Error converting entity ${entity.id} to game: $it") }
                    .getOrNull()
            }
        }
        .flowOn(dispatcher)

    private fun GameEntity.toGame(): Result<Game> {
        val format = format.toFormat()
        if (format == null) return Result.failure(IllegalArgumentException("Unknown format: $format"))

        return Result.success(
            Game(
                id = id,
                name = name,
                players = players,
                format = format,
            ),
        )
    }

    private fun Format.toEntity(): String = when (this) {
        Format.Commander -> "Commander"
        Format.Standard -> "Standard"
        Format.Modern -> "Modern"
    }

    private fun String.toFormat(): Format? = when (this) {
        "Commander" -> Format.Commander
        "Standard" -> Format.Standard
        "Modern" -> Format.Modern
        else -> null
    }
}

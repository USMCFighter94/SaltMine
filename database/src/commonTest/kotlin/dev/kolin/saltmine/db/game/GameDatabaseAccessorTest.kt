package dev.kolin.saltmine.db.game

import dev.kolin.saltmine.core.domain.Format
import dev.kolin.saltmine.core.domain.Game
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.collections.set
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
internal class GameDatabaseAccessorTest {
    private val dao = FakeGameDao()

    private val sut = GameDatabaseAccessor(
        dao = dao,
        dispatcher = UnconfinedTestDispatcher(),
    )

    @Test
    fun `createNewGame - success`() = runTest {
        val actual = sut.createNewGame(
            name = "Test Game",
            players = 2,
            format = Format.Standard,
        )

        val expected = 0L

        assertEquals(expected, actual)
    }

    @Test
    fun `getGame - no data`() = runTest {
        val actual = sut.getGame(-1L)
        assertNull(actual)
    }

    @Test
    fun `getGame - success`() = runTest {
        val key = dao.nextKey++
        val entity = GameEntity(
            id = key,
            name = "Test Game",
            players = 2,
            format = "Standard",
        )

        dao.games[key] = entity

        val expected = Game(
            id = entity.id,
            name = entity.name,
            players = entity.players,
            format = Format.Standard,
        )

        val actual = sut.getGame(key)

        assertEquals(expected, actual)
    }
}

package dev.kolin.saltmine.setup.ui

import dev.kolin.saltmine.core.domain.Format
import dev.kolin.saltmine.db.game.GameDatabaseAccessor
import dev.kolin.saltmine.setup.FakeGameDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class SetupScreenViewModelTest {
    private val sut = SetupScreenViewModel(
        startingState = State(),
        gameDatabaseAccessor = GameDatabaseAccessor(
            dao = FakeGameDao(),
            dispatcher = UnconfinedTestDispatcher(),
        ),
    )

    @Test
    fun onGameNameChanged() = runTest {
        val newName = "A really fun game!"

        sut.onGameNameChanged(newName)

        val actual = sut.state.value.gameName
        assertEquals(newName, actual)
    }

    @Test
    fun onFormatChanged() = runTest {
        val newFormat = Format.Modern

        sut.onFormatChanged(newFormat)

        val actual = sut.state.value.selectedFormat
        assertEquals(newFormat, actual)
    }

    @Test
    fun `onPlayerCountChanged - count above format`() = runTest {
        val newCount = 100

        sut.onPlayerCountChanged(newCount)

        val actual = sut.state.value.players
        assertEquals(sut.state.value.selectedFormat.maxPlayerCount, actual)
    }

    @Test
    fun `onPlayerCountChanged - count below format`() = runTest {
        val newCount = 1

        sut.onPlayerCountChanged(newCount)

        val actual = sut.state.value.players
        assertEquals(sut.state.value.selectedFormat.minPlayerCount, actual)
    }

    @Test
    fun `onPlayerCountChanged - count within format`() = runTest {
        val newCount = 3

        sut.onPlayerCountChanged(newCount)

        val actual = sut.state.value.players
        assertEquals(newCount, actual)
    }
}

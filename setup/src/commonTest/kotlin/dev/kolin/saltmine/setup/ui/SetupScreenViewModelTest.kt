package dev.kolin.saltmine.setup.ui

import dev.kolin.saltmine.core.domain.Format
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SetupScreenViewModelTest {

    private val sut = SetupScreenViewModel(State())

    @Test
    fun onGameNameChanged() {
        val newName = "A really fun game!"

        sut.onGameNameChanged(newName)

        val actual = sut.state.value.gameName
        assertEquals(newName, actual)
    }

    @Test
    fun onFormatChanged() {
        val newFormat = Format.Modern

        sut.onFormatChanged(newFormat)

        val actual = sut.state.value.selectedFormat
        assertEquals(newFormat, actual)
    }

    @Test
    fun `onPlayerCountChanged - count above format`() {
        val newCount = 100

        sut.onPlayerCountChanged(newCount)

        val actual = sut.state.value.players
        assertEquals(sut.state.value.selectedFormat.maxPlayerCount, actual)
    }

    @Test
    fun `onPlayerCountChanged - count below format`() {
        val newCount = 1

        sut.onPlayerCountChanged(newCount)

        val actual = sut.state.value.players
        assertEquals(sut.state.value.selectedFormat.minPlayerCount, actual)
    }

    @Test
    fun `onPlayerCountChanged - count within format`() {
        val newCount = 3

        sut.onPlayerCountChanged(newCount)

        val actual = sut.state.value.players
        assertEquals(newCount, actual)
    }
}
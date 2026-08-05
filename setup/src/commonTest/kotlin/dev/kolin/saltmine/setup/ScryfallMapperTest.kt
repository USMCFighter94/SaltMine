package dev.kolin.saltmine.setup

import dev.kolin.saltmine.setup.response.createFakeScryfallCardResponse
import dev.kolin.saltmine.setup.response.createFakeScryfallResponse
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ScryfallMapperTest {

    private val sut = ScryfallMapper()

    @Test
    fun `null response returns error`() {
        val response = null
        val actual = sut.toDomain(response)

        assertTrue(actual.isFailure)
    }

    @Test
    fun `empty response returns empty list`() {
        val response = createFakeScryfallResponse()
        val actual = sut.toDomain(response)

        assertTrue(actual.isSuccess)
        assertTrue(actual.getOrDefault(emptyList()).isEmpty())
    }

    @Test
    fun `valid response returns list of commanders`() {
        val numOfCommanders = Random.nextInt(1, 11)
        val commanders = (1..numOfCommanders).map { createFakeScryfallCardResponse() }

        val response = createFakeScryfallResponse(
            data = commanders,
        )

        val actual = sut.toDomain(response)
        assertTrue(actual.isSuccess)
        assertEquals(numOfCommanders, actual.getOrDefault(emptyList()).size)
    }
}
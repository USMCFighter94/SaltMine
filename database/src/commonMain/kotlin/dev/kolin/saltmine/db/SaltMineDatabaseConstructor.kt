package dev.kolin.saltmine.db

import androidx.room3.RoomDatabaseConstructor

internal expect object SaltMineDatabaseConstructor : RoomDatabaseConstructor<SaltMineDatabase> {
    override fun initialize(): SaltMineDatabase
}

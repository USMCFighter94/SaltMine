package dev.kolin.saltmine.db

import androidx.room3.RoomDatabaseConstructor

public expect object SaltMineDatabaseConstructor : RoomDatabaseConstructor<SaltMineDatabase> {
    override fun initialize(): SaltMineDatabase
}
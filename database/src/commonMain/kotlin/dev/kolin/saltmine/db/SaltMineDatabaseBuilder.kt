package dev.kolin.saltmine.db

import androidx.room3.RoomDatabase

internal interface SaltMineDatabaseBuilder {
    fun getDatabaseBuilder(): RoomDatabase.Builder<SaltMineDatabase>
}

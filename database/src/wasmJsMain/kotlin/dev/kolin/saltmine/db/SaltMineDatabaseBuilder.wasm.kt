package dev.kolin.saltmine.db

import androidx.room3.Room
import androidx.room3.RoomDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.SingleIn

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
internal class WasmDatabaseBuilder : SaltMineDatabaseBuilder {
    override fun getDatabaseBuilder(): RoomDatabase.Builder<SaltMineDatabase> =
        Room.databaseBuilder<SaltMineDatabase>(
            name = SaltMineDatabase.FILE_NAME,
        )
}

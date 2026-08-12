package dev.kolin.saltmine.db

import androidx.room3.Room
import androidx.room3.RoomDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.SingleIn
import java.io.File

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
internal class DesktopDatabaseBuilder : SaltMineDatabaseBuilder {
    override fun getDatabaseBuilder(): RoomDatabase.Builder<SaltMineDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), SaltMineDatabase.FILE_NAME)
        return Room.databaseBuilder(
            name = dbFile.absolutePath,
        )
    }
}

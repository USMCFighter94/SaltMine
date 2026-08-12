package dev.kolin.saltmine.db

import androidx.room3.Room
import androidx.room3.RoomDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.SingleIn
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
internal class IOSDatabaseBuilder : SaltMineDatabaseBuilder {
    override fun getDatabaseBuilder(): RoomDatabase.Builder<SaltMineDatabase> {
        val dbFilePath = documentDirectory() + "/${SaltMineDatabase.FILE_NAME}"
        return Room.databaseBuilder(
            name = dbFilePath,
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}

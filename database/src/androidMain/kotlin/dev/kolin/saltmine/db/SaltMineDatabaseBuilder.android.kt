package dev.kolin.saltmine.db

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
internal class AndroidDatabaseBuilder(
    private val context: Context,
) : SaltMineDatabaseBuilder {
    override fun getDatabaseBuilder(): RoomDatabase.Builder<SaltMineDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(SaltMineDatabase.FILE_NAME)
        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath,
        )
    }
}

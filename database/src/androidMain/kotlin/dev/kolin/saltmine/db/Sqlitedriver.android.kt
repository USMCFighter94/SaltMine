package dev.kolin.saltmine.db

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
internal class AndroidSqliteDriver : SqliteDriver {
    override fun createSQLiteDriver(): SQLiteDriver = BundledSQLiteDriver()
}

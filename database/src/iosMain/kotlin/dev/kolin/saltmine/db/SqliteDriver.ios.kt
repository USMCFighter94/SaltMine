package dev.kolin.saltmine.db

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
internal class IOSSqliteDriver : SqliteDriver {
    override fun createSQLiteDriver(): SQLiteDriver = BundledSQLiteDriver()
}

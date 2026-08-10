package dev.kolin.saltmine.db

import androidx.sqlite.SQLiteDriver

internal interface SqliteDriver {
    fun createSQLiteDriver(): SQLiteDriver
}

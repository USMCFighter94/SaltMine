package dev.kolin.saltmine.db

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import dev.kolin.saltmine.db.entities.GameEntity

@Database(
    version = 1,
    entities = [
        GameEntity::class,
    ],
)
@ConstructedBy(SaltMineDatabaseConstructor::class)
public abstract class SaltMineDatabase : RoomDatabase() {
    internal abstract fun getGameDao(): GameDao

    public companion object {
        internal const val FILE_NAME = "saltmine.db"
    }
}

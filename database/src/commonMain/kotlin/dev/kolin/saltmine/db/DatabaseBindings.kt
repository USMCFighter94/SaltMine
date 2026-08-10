package dev.kolin.saltmine.db

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@ContributesTo(AppScope::class)
@BindingContainer
public interface DatabaseBindings {
    public companion object {
        @Provides
        @SingleIn(AppScope::class)
        internal fun provideDatabase(
            builder: SaltMineDatabaseBuilder,
            driver: SqliteDriver,
        ): SaltMineDatabase = builder.getDatabaseBuilder()
            .setDriver(driver.createSQLiteDriver())
            .build()

        @Provides
        @SingleIn(AppScope::class)
        internal fun provideGameDao(db: SaltMineDatabase): GameDao = db.getGameDao()
    }
}

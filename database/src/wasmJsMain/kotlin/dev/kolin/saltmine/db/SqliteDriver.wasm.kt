package dev.kolin.saltmine.db

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.SingleIn
import org.w3c.dom.Worker

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
internal class WasmSqliteDriver : SqliteDriver {
    override fun createSQLiteDriver(): SQLiteDriver = WebWorkerSQLiteDriver(getWorker())

}

@OptIn(ExperimentalWasmJsInterop::class)
private fun getWorker(): Worker =
    js("""new Worker(new URL("sqlite-wasm-worker/worker.js", import.meta.url))""")

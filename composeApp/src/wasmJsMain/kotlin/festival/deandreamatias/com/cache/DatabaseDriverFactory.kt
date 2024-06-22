package festival.deandreamatias.com.cache

import app.cash.sqldelight.db.SqlDriver

class WasmDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        throw UnsupportedOperationException("WasmDatabaseDriverFactory is not supported in wasm")
    }
}
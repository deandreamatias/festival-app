package festival.deandreamatias.com.cache

class WasmDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver  {
        throw UnsupportedOperationException("WasmDatabaseDriverFactory is not supported in wasm")
    }
}
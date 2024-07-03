package festival.deandreamatias.com.cache

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

const val DB_NAME = "show.db"

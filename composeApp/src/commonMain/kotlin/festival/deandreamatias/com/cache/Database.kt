package festival.deandreamatias.com.cache

import festival.deandreamatias.com.entity.Show

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllShows(): List<Show> {
        try {
            return dbQuery.selectAllShows(::mapShowSelecting).executeAsList()
        } catch (e: Exception) {
            println("Error to load shows from database: $e")
            return emptyList()
        }
    }

    private fun mapShowSelecting(
        id: String,
        name: String,
        startDate: String,
        startTime: String,
        duration: String,
        genre: String,
        stage: String,
        enabledAlarm: Boolean?
    ): Show {
        return Show(id, name, startDate, startTime, duration, genre, stage, enabledAlarm ?: false)
    }

    internal fun updateShowAlarm(show: Show) {
        println("Updating alarm status for show ${show.id} on database")
        dbQuery.transaction {
            dbQuery.updateShowAlarm(
                enabledAlarm = show.enabledAlarm,
                id = show.id
            )
        }
    }

    internal fun clearAndCreateShows(shows: List<Show>) {
        println("Saving shows to database")
        dbQuery.transaction {
            dbQuery.removeAllShows()
            shows.forEach { show ->
                insertShow(show)
            }
        }
    }

    private fun insertShow(show: Show, transaction: Boolean = false) {
        fun function() =
            dbQuery.insertShow(
                id = show.id,
                name = show.name,
                startDate = show.startDate,
                startTime = show.startTime,
                duration = show.duration,
                genre = show.genre,
                stage = show.stage,
                enabledAlarm = show.enabledAlarm
            )
        if (transaction) dbQuery.transaction {
            function()
        } else function()
    }
}

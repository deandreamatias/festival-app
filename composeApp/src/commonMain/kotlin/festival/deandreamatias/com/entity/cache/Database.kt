package festival.deandreamatias.com.cache
package festival.deandreamatias.com.entity.Show

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllShows(): List<Show> {
        return dbQuery.selectAll(::mapShowSelecting).executeAsList()
    }

    private fun mapShowSelecting(
        id: String,
        name: String,
        startDate: String,
        startTime: String,
        duration: String,
        genre: String,
        stage: String,
    ): Show {
        return Show(id, name, startDate, startTime, duration, genre, stage)
    }

    internal fun clearAndCreateShows(shows: List<Show>) {
        dbQuery.transaction {
            dbQuery.removeAllShows()
            shows.forEach { show ->
                dbQuery.insertShow(
                    id = show.id,
                    name = show.name,
                    startDate = show.startDate,
                    startTime = show.startTime,
                    duration = show.duration,
                    genre = show.genre,
                    stage = show.stage,
                )
            }
        }
    }
}
package festival.deandreamatias.com

import festival.deandreamatias.com.cache.AssetsDatabase
import festival.deandreamatias.com.cache.Database
import festival.deandreamatias.com.cache.DatabaseDriverFactory
import festival.deandreamatias.com.network.MockApi
import festival.deandreamatias.com.entity.Show

class MockSDK(databaseDriverFactory: DatabaseDriverFactory, val api: MockApi, val assetsDatabase: AssetsDatabase) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    suspend fun getShows(forceReload: Boolean): List<Show> {
        val cachedShows = database.getAllShows()
        return if (cachedShows.isNotEmpty() && !forceReload) {
            println("Using cached shows with size: ${cachedShows.size}")
            cachedShows
        } else {
            try {
                api.getAllShows().also {
                    database.clearAndCreateShows(it)
                }
            } catch (e: Exception) {
                println("Error to load shows: $e")
                println("Loading shows from assets")
                assetsDatabase.getAllShows().also {
                    database.clearAndCreateShows(it)
                }
            }
        }
    }
}
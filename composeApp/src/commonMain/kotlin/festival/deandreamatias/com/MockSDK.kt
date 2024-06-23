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
            cachedShows
        } else {
            assetsDatabase.getAllShows().also {
                database.clearAndCreateShows(it)
            }
            // Temporary solution until the API is implemented
//            api.getAllShows().also {
//                database.clearAndCreateShows(it)
//            }
        }
    }
}
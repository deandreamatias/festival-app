package festival.deandreamatias.com.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import festival.deandreamatias.com.entity.Show
import io.ktor.client.call.body

class MockApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getAllShows(): List<Show> {
        return httpClient.get("https://festivalapp2.free.beeceptor.com/").body()
    }
}
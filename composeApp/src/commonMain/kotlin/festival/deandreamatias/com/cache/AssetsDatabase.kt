package festival.deandreamatias.com.cache

import festival.deandreamatias.com.entity.Show
import festival.deandreamatias.com.resources.Res
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi


class AssetsDatabase {
    @OptIn(ExperimentalResourceApi::class)
    internal suspend fun getAllShows(): List<Show> {
        val bytes = Res.readBytes("files/shows.json")
        return Json.decodeFromString(ListSerializer(Show.serializer()), bytes.decodeToString())
    }
}
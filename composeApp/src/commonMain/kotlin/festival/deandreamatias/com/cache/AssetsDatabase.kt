package festival.deandreamatias.com.cache

import festival.deandreamatias.com.entity.Show
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json


class AssetsDatabase {
    internal fun getAllShows(): List<Show> {
        val json = """[{"id":"1","name":"Titusz","start_date":"2019-09-01","start_time":"10:00","duration":"1:00","stage":"Pumpai","genre":"techno"},{"id":"2","name":"Krisztián","start_date":"2019-09-01","start_time":"11:00","duration":"1:00","stage":"Pumpai","genre":"techno"},{"id":"3","name":"Gábor","start_date":"2019-09-01","start_time":"12:00","duration":"1:00","stage":"Pumpai","genre":"techno"},{"id":"4","name":"Gábor","start_date":"2019-09-01","start_time":"13:00","duration":"1:00","stage":"Pumpai","genre":"techno"},{"id":"5","name":"Gábor","start_date":"2019-09-01","start_time":"14:00","duration":"1:00","stage":"Pumpai","genre":"techno"}]"""
        return Json.decodeFromString(ListSerializer(Show.serializer()), json)
    }
}
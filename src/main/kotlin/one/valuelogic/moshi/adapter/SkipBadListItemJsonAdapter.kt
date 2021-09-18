package one.valuelogic.moshi.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

class SkipBadListItemJsonAdapter<T : Any> : JsonAdapter<List<T>>() {

    override fun fromJson(reader: JsonReader): List<T>? {
        TODO("Not yet implemented")
    }

    override fun toJson(writer: JsonWriter, value: List<T>?) {
        TODO("Not yet implemented")
    }

}
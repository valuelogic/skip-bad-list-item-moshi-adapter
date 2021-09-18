package one.valuelogic.moshi.adapter

import com.squareup.moshi.*
import com.squareup.moshi.JsonAdapter.Factory

class SkipBadListItemJsonAdapter<T : Any> private constructor(
    private val itemJsonAdapter: JsonAdapter<T>
) : JsonAdapter<List<T>>() {

    override fun fromJson(reader: JsonReader): List<T> {
        val elements = mutableListOf<T>()
        reader.beginArray()
        while (reader.hasNext()) {
            val peekedReader = reader.peekJson()
            val element: T? = try {
                itemJsonAdapter.fromJson(peekedReader)
            } catch (jsonDataException: JsonDataException) {
                null
            }

            if (element != null) {
                elements.add(element)
            }
            reader.skipValue()
        }
        reader.endArray()
        return elements
    }

    override fun toJson(writer: JsonWriter, value: List<T>?) {
        writer.beginArray()
        value?.forEachIndexed { index, _ ->
            itemJsonAdapter.toJson(writer, value[index])
        }
        writer.endArray()
    }

    companion object {

        val INSTANCE: Factory = Factory { type, annotations, moshi ->
            if (annotations.isEmpty() && Types.getRawType(type) == List::class.java) {
                val elementType = Types.collectionElementType(type, List::class.java)
                SkipBadListItemJsonAdapter(moshi.adapter(elementType))
            } else {
                null
            }
        }
    }
}
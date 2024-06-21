package vn.eclipse.examples.config.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.Instant

class InstantTypeAdapter : TypeAdapter<Instant?>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Instant?) {
        if (value != null) {
            out.value(value.toString())
        } else {
            out.nullValue()
        }
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Instant? {
        val stringValue = `in`.nextString()
        return if (stringValue != null && stringValue.isNotEmpty()) {
            Instant.parse(stringValue)
        } else {
            null
        }
    }
}
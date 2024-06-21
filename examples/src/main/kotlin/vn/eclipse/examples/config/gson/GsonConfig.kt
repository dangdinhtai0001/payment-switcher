package vn.eclipse.examples.config.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Instant

@Configuration
class GsonConfig {
    @Bean
    fun defineGson(): Gson {
        val builder = GsonBuilder()

        return builder
            .registerTypeAdapter(Instant::class.java, InstantTypeAdapter())
            .create()
    }
}
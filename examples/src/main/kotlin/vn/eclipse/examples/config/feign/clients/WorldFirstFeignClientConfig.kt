package vn.eclipse.examples.config.feign.clients

import feign.okhttp.OkHttpClient
import org.springframework.context.annotation.Bean

class WorldFirstFeignClientConfig {
    @Bean
    fun client(): OkHttpClient {
        return OkHttpClient()
    }
}
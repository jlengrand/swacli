package nl.lengrand.swacli

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json

object Configuration {

    private val jsonSerializer = Json { ignoreUnknownKeys = true}

    fun getHttpClient() = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(jsonSerializer)
        }
    }
}
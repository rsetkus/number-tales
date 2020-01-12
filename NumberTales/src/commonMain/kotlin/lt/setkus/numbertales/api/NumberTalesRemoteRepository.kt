package lt.setkus.numbertales.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import lt.setkus.numbertales.NumberTalesRepository
import lt.setkus.numbertales.model.Tale

class NumberTalesRemoteRepository : NumberTalesRepository {

    private val baseUrl = "http://numbersapi.com/random/trivia?json"

    private val client by lazy {
        HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json(JsonConfiguration(strictMode = false)))
            }
        }
    }

    override suspend fun fetchNumberTale(): Tale = client.get(baseUrl)
}
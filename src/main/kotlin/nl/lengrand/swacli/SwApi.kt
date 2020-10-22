package nl.lengrand.swacli

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

const val BASE_URL = "https://swapi.dev/api"

object SwApi {

    private val httpClient = Configuration.getHttpClient()

    suspend fun getPeople(query : String?) : Response<People> {
        return httpClient.get("$BASE_URL/people/${queryString(query)}") {
            header("Content-Type", ContentType.Application.Json.toString())
        }
    }

    suspend fun getPlanets(query : String?) : Response<Planet> {
        return httpClient.get("$BASE_URL/planets/${queryString(query)}") {
            header("Content-Type", ContentType.Application.Json.toString())
        }
    }

    private fun queryString(query: String?) = if(query == null)  "" else  "?search=${query}"
}

@Serializable sealed class Data
@Serializable data class Response<Data>(val count: Int, val next : String?, val previous : String?, val results : List<Data>)
@Serializable data class Planet(val climate: String, val name: String, val gravity: String, val orbital_period: String, val diameter: String) : Data()
@Serializable data class People(val name: String, val height: String, val mass: String, val hair_color: String, val homeworld: String, val birth_year: String) : Data()
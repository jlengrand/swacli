package nl.lengrand.swacli

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.kittinunf.fuel.Fuel
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

val mapper: ObjectMapper = ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

const val BASE_URL = "https://swapi.dev/api"

object SwApi {

    private val httpClient = Configuration.getHttpClient()

    suspend fun getPeople(query : String?) =
        httpClient.get<People>("$BASE_URL/people/${queryString(query)}") {
            header("Content-Type", ContentType.Application.Json.toString())
        }


//        return Fuel.get("$BASE_URL/people/${queryString(query)}")
//            .header("accept", "application/json")
//            .responseObject<Response<People>>(mapper).third.get()



    fun getPlanets(query : String?) : Response<Planet> {
        return Fuel.get("$BASE_URL/planets/${queryString(query)}")
            .header("accept", "application/json")
            .responseObject<Response<Planet>>(mapper).third.get()
    }

    private fun queryString(query: String?) = if(query == null)  "" else  "?search=${query}"
}

@Serializable
sealed class Data
@Serializable
data class Response<Data>(val count: Int, val next : String?, val previous : String?, val results : List<Data>)
@Serializable
data class Planet(val climate: String, val name: String, val gravity: String, val orbital_period: String, val diameter: String) : Data()
@Serializable
data class People(val name: String, val height: String, val mass: String, val hair_color: String, val homeworld: String, val birth_year: String) : Data()
package nl.lengrand.swacli

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.responseObject
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

const val BASE_URL = "https://swapi.dev/api"

object SwApi {

    private val jsonSerializer = Json { ignoreUnknownKeys = true}
    private val peopleDeserializer = Response.serializer(People.serializer())
    private val planetsDeserializer = Response.serializer(Planet.serializer())

    fun getPeople(query : String?) : Response<People> {
        return "$BASE_URL/people/${queryString(query)}".httpGet()
            .header("accept", "application/json")
            .responseObject(json = jsonSerializer, loader = peopleDeserializer).third.get()
    }

    fun getPlanets(query : String?) : Response<Planet> {
        return "$BASE_URL/planets/${queryString(query)}".httpGet()
            .header("accept", "application/json")
            .responseObject(json = jsonSerializer, loader = planetsDeserializer).third.get()
    }

    private fun queryString(query: String?) = if(query == null)  "" else  "?search=${query}"
}

@Serializable sealed class Data
@Serializable data class Response<Data>(val count: Int, val next : String?, val previous : String?, val results : List<Data>)
@Serializable data class Planet(val climate: String, val name: String, val gravity: String, val orbital_period: String, val diameter: String) : Data()
@Serializable data class People(val name: String, val height: String, val mass: String, val hair_color: String, val homeworld: String, val birth_year: String) : Data()
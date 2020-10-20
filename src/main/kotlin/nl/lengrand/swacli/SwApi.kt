package nl.lengrand.swacli

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.jackson.responseObject

val mapper: ObjectMapper = ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

const val BASE_URL = "https://swapi.dev/api"

class SwApi {
    companion object{
        fun getPeople(query : String?) : Response<People> {
            return Fuel.get("$BASE_URL/people/${queryString(query)}")
                .header("accept", "application/json")
                .responseObject<Response<People>>(mapper).third.get()
        }

        fun getPlanets(query : String?) : Response<Planet> {
            return Fuel.get("$BASE_URL/planets/${queryString(query)}")
                .header("accept", "application/json")
                .responseObject<Response<Planet>>(mapper).third.get()
        }

        private fun queryString(query: String?) = if(query == null)  "" else  "?search=${query}"
    }
}

data class Response<T>(val count: Int, val next : String?, val previous : String?, val results : List<T>)
data class Planet(val climate: String, val name: String, val gravity: String, val orbital_period: String, val diameter: String)
data class People(val name: String, val height: String, val mass: String, val hair_color: String, val homeworld: String, val birth_year: String)
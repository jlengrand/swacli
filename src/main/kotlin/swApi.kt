import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.jackson.responseObject

val mapper: ObjectMapper = ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

const val BASE_URL = "https://swapi.dev/api/"

class SwApi {
    companion object{

        fun getPeople() : PeopleResponse {
            return Fuel.get("${BASE_URL}people/")
                .header("accept", "application/json")
                .responseObject<PeopleResponse>(mapper).third.get()
        }
    }
}

data class PeopleResponse(val count: Int, val next : String?, val previous : String?, val results : List<People>)
data class People(val name: String, val height: Int, val mass: Int, val hair_color: String, val homeworld: String, val birth_year: String)
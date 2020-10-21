package nl.lengrand.swacli

import picocli.CommandLine.Help.Ansi

class PrettyPrinter {
    companion object {
        fun <T : Data> print(response: Response<T>){

            println("""
                ${Ansi.AUTO.string("@|bold,green Found ${response.count} total results for that query|@")}
                ${if (response.count > 0) Ansi.AUTO.string("@|underline,green Showing ${response.results.size} results|@") else ""}
                
            """.trimIndent())

            response.results.map {
                when(it){
                    is People -> printPeople(it)
                    is Planet -> printPlanet(it)
                }
            }
        }

        private fun printPeople(people : People){
            println("""
                ${Ansi.AUTO.string("@|bold,yellow Name : ${people.name}|@")}
                ${Ansi.AUTO.string("""@|reset 
                    Birth year : ${people.birth_year}
                    Height : ${people.height}
                    Weight : ${people.mass}
                    Hair color : ${people.hair_color}
                    Homeworld : ${people.homeworld}
                |@""".trimIndent())}
            """.trimIndent())
        }

        private fun printPlanet(planet : Planet){
            println("""
                ${Ansi.AUTO.string("@|bold,yellow Name : ${planet.name}|@")}
                ${Ansi.AUTO.string("""@|reset 
                    Climate : ${planet.climate}
                    Diameter (km) : ${planet.diameter}
                    Gravity : ${planet.gravity}
                    Orbital period : ${planet.orbital_period}
                |@""".trimIndent())}
            """.trimIndent())
        }
    }
}
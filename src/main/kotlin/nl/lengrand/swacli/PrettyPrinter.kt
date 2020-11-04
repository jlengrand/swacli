package nl.lengrand.swacli

import picocli.CommandLine
import picocli.CommandLine.Help.Ansi

const val asciiArt =
"""
______     __     __     ______     ______     __         __    
/\  ___\   /\ \  _ \ \   /\  __ \   /\  ___\   /\ \       /\ \   
\ \___  \  \ \ \/ ".\ \  \ \  __ \  \ \ \____  \ \ \____  \ \ \  
 \/\_____\  \ \__/".~\_\  \ \_\ \_\  \ \_____\  \ \_____\  \ \_\ 
  \/_____/   \/_/   \/_/   \/_/\/_/   \/_____/   \/_____/   \/_/
"""

class PrettyPrinter(val spec : CommandLine.Model.CommandSpec) {
    fun <T : Data> print(response: Response<T>){

        spec.commandLine().out.println("""
            ${Ansi.AUTO.string("@|bold,green \uD83D\uDE80 Found ${response.count} total results for that query \uD83D\uDE80 |@")}
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
        spec.commandLine().out.println("""
            ${Ansi.AUTO.string("@|bold,yellow \uD83D\uDC7D ${people.name}|@")}
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
        spec.commandLine().out.println("""
            ${Ansi.AUTO.string("@|bold,yellow \uD83E\uDE90 ${planet.name}|@")}
            ${Ansi.AUTO.string("""@|reset 
                Climate : ${planet.climate}
                Diameter (km) : ${planet.diameter}
                Gravity : ${planet.gravity}
                Orbital period : ${planet.orbital_period}
            |@""".trimIndent())}
        """.trimIndent())
    }
}
package nl.lengrand.swacli

import picocli.CommandLine

class PrettyPrinter {
    companion object {
        fun <T : Data> print(response: Response<T>){
            println(CommandLine.Help.Ansi.AUTO.string("@|bold,green Found ${response.count} total results for that query|@"))
            if (response.count > 0) println(CommandLine.Help.Ansi.AUTO.string("@|underline,green Showing ${response.results.size} results|@"))

            response.results.map {
                when(it){
                    is People -> printPeople(it)
                    is Planet -> printPlanet(it)
                }
            }
            println()
        }

        private fun printPeople(people : People){
            println()
            println(CommandLine.Help.Ansi.AUTO.string("@|bold,yellow Name : ${people.name}|@"))
            println()
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Birth year : ${people.birth_year}|@"))
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Height : ${people.height}|@"))
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Weight : ${people.mass}|@"))
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Hair color : ${people.hair_color}|@"))
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Homeworld : ${people.homeworld}|@"))
        }

        private fun printPlanet(planet : Planet){
            println()
            println(CommandLine.Help.Ansi.AUTO.string("@|bold,yellow Name: ${planet.name}|@"))
            println()
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Climate : ${planet.climate}|@"))
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Diameter (km) : ${planet.diameter}|@"))
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Gravity : ${planet.gravity}|@"))
            println(CommandLine.Help.Ansi.AUTO.string("@|reset      Orbital period : ${planet.orbital_period}|@"))
        }
    }
}
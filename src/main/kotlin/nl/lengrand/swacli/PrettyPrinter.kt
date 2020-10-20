package nl.lengrand.swacli

import picocli.CommandLine

class PeoplePrinter{
    companion object{
        fun prettyPrint(peopleResponse: Response<People>){
            println(CommandLine.Help.Ansi.AUTO.string("@|bold,green Found ${peopleResponse.count} results for that query|@"))
            println(CommandLine.Help.Ansi.AUTO.string("@|bold,green Found ${peopleResponse.count} results for that query|@"))

            println(CommandLine.Help.Ansi.AUTO.string("@|underline,green Showing ${peopleResponse.results.size} characters|@"))

            peopleResponse.results.map {
                println()
                println(CommandLine.Help.Ansi.AUTO.string("@|bold,yellow Name : ${it.name}|@"))
                println()
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Birth year : ${it.birth_year}|@"))
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Height : ${it.height}|@"))
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Weight : ${it.mass}|@"))
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Hair color : ${it.hair_color}|@"))
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Homeworld : ${it.homeworld}|@"))
            }
            println()
        }
    }
}

class PlanetsPrinter{
    companion object{
        fun prettyPrint(planetResponse: Response<Planet>) {
            println(CommandLine.Help.Ansi.AUTO.string("@|bold,green Found ${planetResponse.count} results for that query|@"))

            println(CommandLine.Help.Ansi.AUTO.string("@|underline,green Showing ${planetResponse.results.size} characters|@"))

            planetResponse.results.map {
                println()
                println(CommandLine.Help.Ansi.AUTO.string("@|bold,yellow Name: ${it.name}|@"))
                println()
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Climate : ${it.climate}|@"))
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Diameter (km) : ${it.diameter}|@"))
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Gravity : ${it.gravity}|@"))
                println(CommandLine.Help.Ansi.AUTO.string("@|reset      Orbital period : ${it.orbital_period}|@"))
            }
            println()
        }
    }
}
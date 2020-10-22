package nl.lengrand.swacli

import kotlinx.coroutines.runBlocking
import picocli.CommandLine
import picocli.CommandLine.*
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(
    name = "sw",
    version = ["0.1"],
    mixinStandardHelpOptions = true,
    description = ["A Star Wars CLI built on top of https://swapi.dev/"]
)
class SwaCLIOptions : Callable<Int> {

    @Parameters(index = "0", arity = "0..1", description = ["Search query for the request. (Example : Anakin)"])
    private var searchQuery : String? = null

    @ArgGroup(exclusive = true, multiplicity = "1")
    private lateinit var exclusive: Exclusive

    internal class Exclusive {
        @Option(names = ["-c", "--characters"], required = true, description = ["Search for characters"])
        var characters = false

        @Option(names = ["-p", "--planets"], required = true, description = ["Search for planets"])
        var planets = false
    }

    override fun call(): Int {
        runBlocking {
            if (exclusive.characters)
                PrettyPrinter.print(SwApi.getPeople(searchQuery))
            if (exclusive.planets)
                PrettyPrinter.print(SwApi.getPlanets(searchQuery))
        }

        return 0
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            exitProcess(CommandLine(SwaCLIOptions()).execute(*args))
        }
    }
}
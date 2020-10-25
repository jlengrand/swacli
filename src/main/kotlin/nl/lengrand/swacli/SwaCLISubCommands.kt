package nl.lengrand.swacli

import kotlinx.coroutines.runBlocking
import picocli.CommandLine
import picocli.CommandLine.*
import picocli.CommandLine.Model.*
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(
    name = "sw",
    version = ["0.1"],
    mixinStandardHelpOptions = true,
    description = [asciiArt, "@|bold,yellow \uD83E\uDE90 A Star Wars CLI built on top of https://swapi.dev/ \uD83E\uDE90 |@"],
    subcommands = [PlanetsCommandPaginate::class, PeopleCommandPaginate::class, HelpCommand::class]
)
class SwaCLISubCommands : Callable<Int> {
    @Spec
    lateinit var spec: CommandSpec

    override fun call(): Int {
        spec.commandLine().usage(System.out)
        return 0
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            exitProcess(CommandLine(SwaCLISubCommands()).execute(*args))
        }
    }
}

@Command(name = "planets", description = ["Search for planets"])
class PlanetsCommand : Callable<Int> {
    @Spec
    lateinit var spec: CommandSpec

    @Parameters(index = "0", arity = "0..1", description = ["Search query for the request. (Example : Tatooine)"])
    private var searchQuery : String? = null

    override fun call(): Int {
        runBlocking {
            PrettyPrinter(spec).print(SwApi.getPlanets(searchQuery))
        }
        return 0
    }
}

@Command(name = "people", description = ["Search for people"])
class PeopleCommand : Callable<Int> {
    @Spec
    lateinit var spec: CommandSpec

    @Parameters(index = "0", arity = "0..1", description = ["Search query for the request. (Example : Anakin)"])
    private var searchQuery : String? = null

    override fun call(): Int {
        runBlocking {
            PrettyPrinter(spec).print(SwApi.getPeople(searchQuery))
        }
        return 0
    }
}
package nl.lengrand.swacli

import picocli.CommandLine
import picocli.CommandLine.*
import picocli.CommandLine.Model.*
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(
    name = "sw",
    version = ["0.1"],
    mixinStandardHelpOptions = true,
    description = ["A Star Wars CLI built on top of https://swapi.dev/"],
    subcommands = [PlanetsCommand::class, PeopleCommand::class, HelpCommand::class]
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

    @Parameters(index = "0", arity = "0..1", description = ["Search query for the request. (Example : Tatooine)"])
    private var searchQuery : String? = null

    override fun call(): Int {
        PrettyPrinter.print(SwApi.getPlanets(searchQuery))
        return 0
    }
}

@Command(name = "people", description = ["Search for people"])
class PeopleCommand : Callable<Int> {

    @Parameters(index = "0", arity = "0..1", description = ["Search query for the request. (Example : Anakin)"])
    private var searchQuery : String? = null

    override fun call(): Int {
        PrettyPrinter.print(SwApi.getPeople(searchQuery))
        return 0
    }
}
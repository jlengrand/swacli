package nl.lengrand.swacli

import picocli.CommandLine
import picocli.CommandLine.*
import picocli.CommandLine.Model.CommandSpec
import java.util.concurrent.Callable
import kotlin.system.exitProcess

class RunPaginate : RunLast() {
}

@Command(
        name = "sw",
        version = ["0.1"],
        mixinStandardHelpOptions = true,
        description = [asciiArt, "@|bold,yellow \uD83E\uDE90 A Star Wars CLI built on top of https://swapi.dev/ \uD83E\uDE90 |@"],
        subcommands = [PlanetsCommandPaginate::class, HelpCommand::class]
)
class SwaCLIPaginate : Callable<Int> {
    @Spec
    lateinit var spec: CommandSpec

    private fun executionStrategy(parseResult: ParseResult): Int {
        return RunPaginate().execute(parseResult)
    }

    override fun call(): Int {
        spec.commandLine().usage(System.out)
        return 0
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            val app = SwaCLIPaginate()
            exitProcess(CommandLine(app)
                    .setExecutionStrategy(app::executionStrategy)
                    .execute(*args))
        }
    }
}

@Command(name = "planets", description = ["Search for planets"])
class PlanetsCommandPaginate : Callable<Int> {
    @Spec
    lateinit var spec: CommandSpec

    @Parameters(index = "0", arity = "0..1", description = ["Search query for the request. (Example : Tatooine)"])
    private var searchQuery : String? = null

    override fun call(): Int {
        PrettyPrinter(spec).print(SwApi.getPlanets(searchQuery))
        return 0
    }
}
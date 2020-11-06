package nl.lengrand.swacli

import picocli.CommandLine
import picocli.CommandLine.*
import picocli.CommandLine.Model.*
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(
    name = "sw",
    version = ["0.2"],
    mixinStandardHelpOptions = true,
    description = [asciiArt, "@|bold,yellow \uD83E\uDE90 A Star Wars CLI built on top of https://swapi.dev/ \uD83E\uDE90 |@"],
    subcommands = [PlanetsCommand::class, PeopleCommand::class, HelpCommand::class]
)
class SwaCLIPaginate : Callable<Int> {

    @Spec
    lateinit var spec: CommandSpec

    private fun executionStrategy(parseResult: ParseResult): Int {

        if (!parseResult.hasSubcommand())
            return RunLast().execute(parseResult)

        val file = Files.createTempFile("pico", ".tmp").toFile()
        this.spec.commandLine().out = PrintWriter(FileWriter(file), true)

        val result = RunLast().execute(parseResult)

        val processBuilder = ProcessBuilder("less", file.absolutePath).inheritIO()
        val process = processBuilder.start()
        process.waitFor()

        return result
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
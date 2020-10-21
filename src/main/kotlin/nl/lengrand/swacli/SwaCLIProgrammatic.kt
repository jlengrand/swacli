package nl.lengrand.swacli

import picocli.CommandLine
import picocli.CommandLine.IExecutionStrategy
import picocli.CommandLine.Model.CommandSpec
import picocli.CommandLine.Model.PositionalParamSpec
import picocli.CommandLine.ParseResult
import kotlin.system.exitProcess

object SwaCLIProgrammatic {

    private fun run(parseResult: ParseResult): Int {
        val helpExitCode = CommandLine.executeHelpRequest(parseResult)
        if (helpExitCode != null) return helpExitCode

        if (parseResult.hasSubcommand()){
            val subResult = parseResult.subcommand()
            val searchQuery = if (subResult.hasMatchedPositional(0)) subResult.matchedPositional(0).stringValues()[0] else null

            if (subResult.commandSpec().name() == "planets")
                PlanetsPrinter.prettyPrint(SwApi.getPlanets(searchQuery))
            if (subResult.commandSpec().name() == "people")
                PeoplePrinter.prettyPrint(SwApi.getPeople(searchQuery))
        }
        return 0
    }

    @JvmStatic
    fun main(args: Array<String>) {

        val commandLine = CommandLine(CommandSpec.create().mixinStandardHelpOptions(true))
        val planetsPeopleSpec = CommandSpec.create()

        planetsPeopleSpec.addPositional(
            PositionalParamSpec.builder()
                .paramLabel("searchQuery")
                .description("Search query for the request. (Example: Tatooine").build()
        )

        commandLine.addSubcommand("planets", CommandLine(planetsPeopleSpec))
        commandLine.addSubcommand("people", CommandLine(planetsPeopleSpec))

        commandLine.executionStrategy = IExecutionStrategy { run(it) }
        exitProcess(commandLine.execute(*args))
    }
}
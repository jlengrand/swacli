package nl.lengrand.swacli

import picocli.CommandLine
import picocli.CommandLine.*
import picocli.CommandLine.Model.CommandSpec
import java.io.*
import java.util.concurrent.Callable
import kotlin.system.exitProcess

const val dummyLongString =
"""
🚀 Found 60 total results for that query 🚀 
Showing 10 results

🪐 Tatooine

    Climate : arid
    Diameter (km) : 10465
    Gravity : 1 standard
    Orbital period : 304

🪐 Alderaan

    Climate : temperate
    Diameter (km) : 12500
    Gravity : 1 standard
    Orbital period : 364

🪐 Yavin IV

    Climate : temperate, tropical
    Diameter (km) : 10200
    Gravity : 1 standard
    Orbital period : 4818

🪐 Hoth

    Climate : frozen
    Diameter (km) : 7200
    Gravity : 1.1 standard
    Orbital period : 549

🪐 Dagobah

    Climate : murky
    Diameter (km) : 8900
    Gravity : N/A
    Orbital period : 341

🪐 Bespin

    Climate : temperate
    Diameter (km) : 118000
    Gravity : 1.5 (surface), 1 standard (Cloud City)
    Orbital period : 5110

🪐 Endor

    Climate : temperate
    Diameter (km) : 4900
    Gravity : 0.85 standard
    Orbital period : 402

🪐 Naboo

    Climate : temperate
    Diameter (km) : 12120
    Gravity : 1 standard
    Orbital period : 312

🪐 Coruscant

    Climate : temperate
    Diameter (km) : 12240
    Gravity : 1 standard
    Orbital period : 368

🪐 Kamino

    Climate : temperate
    Diameter (km) : 19720
    Gravity : 1 standard
    Orbital period : 463
"""

@Command(
        mixinStandardHelpOptions = true,
        subcommands = [PlanetsCommandPaginateDummy::class, HelpCommand::class]
)
class SwaCLIPaginateDummy : Callable<Int> {
    @Spec
    lateinit var spec: CommandSpec

    private fun executionStrategy(parseResult: ParseResult): Int {
        println("in there")
        val processBuilder = ProcessBuilder("less")
        val process = processBuilder.start()
        this.spec.commandLine().out = PrintWriter(process.outputStream)
        return RunLast().execute(parseResult)
    }

    override fun call(): Int {
        spec.commandLine().usage(System.out)
        return 0
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            val app = SwaCLIPaginateDummy()
            exitProcess(CommandLine(app)
                    .setExecutionStrategy(app::executionStrategy)
                    .execute(*args))
        }
    }
}

@Command(name = "planets", description = ["Search for planets"])
class PlanetsCommandPaginateDummy : Callable<Int> {

    override fun call(): Int {
        println(dummyLongString)
        return 0
    }
}
package nl.lengrand.swacli

import picocli.CommandLine
import picocli.CommandLine.Command
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(name= "sw", version= ["0.1"], mixinStandardHelpOptions = true, description = ["A Star Wars CLI built on top of https://swapi.dev/"])
class SwaCLIBasic : Callable<Int> {
    override fun call(): Int {
        println("I'm running!")
        return 0
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            exitProcess(CommandLine(SwaCLIBasic()).execute(*args))
        }
    }
}
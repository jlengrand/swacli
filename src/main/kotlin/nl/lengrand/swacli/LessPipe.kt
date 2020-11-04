package nl.lengrand.swacli

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val longString =
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

class PipeToLess{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            val processBuilder = ProcessBuilder("less")
//            processBuilder.redirectErrorStream(true);
            val process = processBuilder.start()

            val inputStream = process.outputStream
            val outputStream = process.inputStream

            val writer = BufferedWriter(OutputStreamWriter(inputStream ))
            val reader = BufferedReader(InputStreamReader(outputStream))

//            writer.write(longString)
            reader.read(longString.toCharArray())
//            reader.close()
            writer.flush()
            writer.close()
        }
    }
}

class LessPipe2 {
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            val p = Runtime.getRuntime().exec(
                arrayOf(
                    "sh", "-c",
                    "less $longString"
                )
            )
            println("=> " + p.waitFor())
        }

    }
}

import java.util.concurrent.Callable

class SwaCLI : Callable<Int> {
    override fun call(): Int {
        println("Called my method!")
        return 2
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            println(SwApi.getPeople().results)
            println("//////////")
            println(SwApi.getPlanets().results)
        }
    }
}
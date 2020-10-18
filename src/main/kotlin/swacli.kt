import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class SwaCLI : Callable<Int> {
    override fun call(): Int {
        println("Called my method!")
        return 2
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            val executor = Executors.newFixedThreadPool(1)
            val future: Future<Int> = executor.submit(SwaCLI())
            println(Date().toString() + "::" + future.get())
            executor.shutdown()

            println(SwApi.getPeople().results)
        }
    }
}
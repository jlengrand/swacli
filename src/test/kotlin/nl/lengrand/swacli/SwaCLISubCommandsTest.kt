package nl.lengrand.swacli

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import picocli.CommandLine
import java.io.PrintWriter
import java.io.StringWriter

class SwaCLISubCommandsTest {

    private val outStringWriter = StringWriter()
    private val errStringWriter = StringWriter()
    private lateinit var commandLine: CommandLine

    @BeforeEach
    fun before(){
        commandLine = CommandLine(SwaCLISubCommands())
        commandLine.out = PrintWriter(outStringWriter)
        commandLine.err = PrintWriter(errStringWriter)
    }

    @Test
    fun `testing an unknown command and expecting invalid input`() {
        val exitCode: Int = commandLine.execute("ships")
        assertEquals(2, exitCode)
        println(errStringWriter.toString())
        assumeTrue(errStringWriter.toString().contains("Unmatched argument"));
        assumeTrue(outStringWriter.toString().isEmpty());
    }

    @Test
    fun `testing all planets works`() {
        val exitCode: Int = commandLine.execute("planets")
        assertEquals(0, exitCode)
        assumeTrue(errStringWriter.toString().isEmpty());
        assumeTrue(outStringWriter.toString().isNotEmpty());
    }

    @Test
    fun `testing a specific character works`() {
        val exitCode: Int = commandLine.execute("people", "Anakin")
        assertEquals(0, exitCode)
        assumeTrue(errStringWriter.toString().isEmpty());
        assumeTrue(outStringWriter.toString().isNotEmpty());
    }
}
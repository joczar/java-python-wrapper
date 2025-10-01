package com.example.java.python.wrapper

import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.PumpStreamHandler
import spock.lang.Specification

class PythonExecTest extends Specification {

    def "test running simple Python script"() {
        given:
        File file = new File(path + fileName)

        String line = "python " + file.getAbsolutePath()
        CommandLine cmdLine = CommandLine.parse(line)

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream)

        DefaultExecutor executor = new DefaultExecutor()
        executor.setStreamHandler(streamHandler)

        when:
        int exitCode = executor.execute(cmdLine)

        then:
        exitCode == 0
        outputStream.toString().trim() == result

        where:
        fileName                    |path                   |result
        "test_python_script.py"     |"src/test/resources/"  |"tested"
    }
}
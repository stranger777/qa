package io.github.stranger777.qa.task2;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.out;

public class MainSetEnvByArgs {
    public static void main(String[] args) throws IOException {
        String processName = args[0];
        String envVarValue = args[1];
        String envVarName = args[2];
        ProcessBuilder processBuilder = new ProcessBuilder(processName);
        Map<String, String> environment = processBuilder.environment();

        environment.put(envVarName, envVarValue);

        out.println(
                "Environment variable " +
                        envVarName + " for " + processName + " (set in pom.xml): " + processBuilder.environment().get(envVarName));
    }
}

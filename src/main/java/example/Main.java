package example;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

public class Main implements Runnable {

    @Option(names = {"-n", "--name"}, description = "User's name", required = true)
    String name;

    @Option(names = {"-a", "--age"}, description = "User's age")
    int age = 0;

    @Option(names = {"-f", "--file"}, description = "File with complete path", required = true)
    File file;

    @Parameters(index = "0", description = "Action to perform", arity = "1")
    String action;

    @Override
    public void run() {
        if (!file.exists() || !file.isFile()) {
            System.err.println("Error: File does not exist: " + file.getAbsolutePath());
            System.exit(1);
        }
        if (file.length() == 0) {
            System.err.println("Error: File is empty: " + file.getAbsolutePath());
            System.exit(1);
        }
        System.out.printf("Action: %s%nName: %s%nAge: %d%nFile: %s%n", action, name, age, file.getAbsolutePath());
    }

    public static void main(String[] args) {
        CommandLine.run(new Main(), args);
    }
}
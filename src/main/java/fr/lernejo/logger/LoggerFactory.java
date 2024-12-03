package fr.lernejo.logger;

import java.util.function.Predicate;

public class LoggerFactory {

    public static Logger getLogger(String name) {
        Logger consoleLogger = new ConsoleLogger();
        Logger fileLogger = new FileLogger("target/captain.log");

        Logger contextualConsoleLogger = new ContextualLogger(name, consoleLogger);

        Predicate<String> filterCondition = message -> message.contains("Simulation");
        Logger filteredFileLogger = new FilteredLogger(
                new ContextualLogger(name, fileLogger),
                filterCondition
        );

        return new CompositeLogger(contextualConsoleLogger, filteredFileLogger);
    }
}
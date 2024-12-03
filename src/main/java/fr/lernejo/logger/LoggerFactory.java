package fr.lernejo.logger;

public class LoggerFactory {

    public static Logger getLogger(String name) {
        Logger consoleLogger = new ConsoleLogger();
        Logger fileLogger = new FileLogger("target/captain.log");

        Logger contextualConsoleLogger = new ContextualLogger(name, consoleLogger);
        Logger contextualFileLogger = new ContextualLogger(name, fileLogger);

        return new CompositeLogger(contextualConsoleLogger, contextualFileLogger);
    }
}

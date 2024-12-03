package fr.lernejo.logger;

public class LoggerFactory {

    public static Logger getLogger(String name) {
        Logger fileLogger = new FileLogger("target/captain.log");
        Logger contextualLogger = new ContextualLogger(name, fileLogger);
        return contextualLogger;
    }
}
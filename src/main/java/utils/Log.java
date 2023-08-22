package utils;

import org.apache.log4j.Logger;

public class Log {
    // Initialize Log4j logger
    private static final Logger log = Logger.getLogger(Log.class.getName());

    // Log methods
    public static void info(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
    }

    public static void debug(String message) {
        log.debug(message);

    }


}

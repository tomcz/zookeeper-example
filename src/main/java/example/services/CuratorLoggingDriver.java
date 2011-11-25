package example.services;

import com.netflix.curator.drivers.LoggingDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorLoggingDriver implements LoggingDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger("CURATOR");

    public void debug(String message) {
        LOGGER.debug(message);
    }

    public void debug(Throwable err) {
        LOGGER.debug(err.toString(), err);
    }

    public void debug(String message, Throwable err) {
        LOGGER.debug(message, err);
    }

    public void info(String message) {
        LOGGER.info(message);
    }

    public void info(Throwable err) {
        LOGGER.info(err.toString(), err);
    }

    public void info(String message, Throwable err) {
        LOGGER.info(message, err);
    }

    public void warn(String message) {
        LOGGER.warn(message);
    }

    public void warn(Throwable err) {
        LOGGER.warn(err.toString(), err);
    }

    public void warn(String message, Throwable err) {
        LOGGER.warn(message, err);
    }

    public void error(String message) {
        LOGGER.error(message);
    }

    public void error(Throwable err) {
        LOGGER.error(err.toString(), err);
    }

    public void error(String message, Throwable err) {
        LOGGER.error(message, err);
    }
}

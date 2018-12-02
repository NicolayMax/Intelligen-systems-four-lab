package ru.bestcoders.aicarsuperracing.utils;

import ru.bestcoders.aicarsuperracing.gui.LogWindow;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

public class LogHandler extends Handler {

    private LogWindow window;
    private static LogHandler handler;

    private LogHandler() {
        LogManager manager = LogManager.getLogManager();
        String className = this.getClass().getName();
        String level = manager.getProperty(className + ".level");
        setLevel(level != null ? Level.parse(level) : Level.INFO);
        if (window == null)
            window = new LogWindow();
    }

    public static synchronized LogHandler getInstance() {
        if (handler == null) {
            handler = new LogHandler();
        }
        return handler;
    }

    public synchronized void publish(LogRecord record) {
        if (!isLoggable(record)) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[").append(record.getLevel()).append("] : ").append(record.getMessage()).append("\n");
        String message = builder.toString();
        window.showInfo(message);
    }

    public void close() {
    }

    public void flush() {
    }
}

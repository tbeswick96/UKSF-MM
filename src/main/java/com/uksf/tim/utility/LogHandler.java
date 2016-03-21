package com.uksf.tim.utility;

import java.io.*;

import static com.uksf.tim.core.Core.error;
import static com.uksf.tim.utility.Info.*;
import static com.uksf.tim.utility.LogHandler.Severity.INFO;

@SuppressWarnings("ALL")
public class LogHandler {

    /**
     * Log File
     */
    private static File logFile;

    /**
     * Log handler. Prints internal program outputs to log file
     */
    public LogHandler() {
        logFile = new File(APPDATA + "\\UKSF-MM\\MM__" + DATEFORMAT.format(DATE) + ".log");
        System.out.println(logFile.getAbsolutePath());
        try {
            logFile.createNewFile();
        } catch(IOException e) {
            error(e);
        }
        logSeverity(INFO, "Log Created");
        LogHandler.logNoTime(HASHSPACE);
    }

    /**
     * Print log with no severity
     * @param log message to print
     */
    public static void log(String log) {
        log = TIMEFORMAT.format(DATE) + " " + log;
        System.out.println(log);
        toFile(log);
    }

    /**
     * Prints log with severity
     * @param severity log severity
     * @param log message to print
     */
    public static void logSeverity(Severity severity, String log) {
        log = TIMEFORMAT.format(DATE) + " " + severity + ": " + log;
        System.out.println(log);
        toFile(log);
    }

    /**
     * Prints log with no severity or time
     * @param log message to print
     */
    public static void logNoTime(String log) {
        System.out.println(log);
        toFile(log);
    }

    /**
     * Writes to file
     * @param log formatted message to write
     */
    private static void toFile(String log) {
        try {
            logFile.setWritable(true);
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true)));
            writer.append(log + "\n");
            writer.close();
            logFile.setReadOnly();
        } catch(IOException e) {
            error(e);
        }
    }

    /**
     * Closes log
     */
    public static void closeLog() {
        LogHandler.logNoTime(HASHSPACE);
        logSeverity(INFO, "Log Closing");
        logFile.setWritable(true);
    }

    /**
     * Severity types
     */
    public enum Severity {
        INFO,
        DEBUG,
        WARNING,
        ERROR,
        CRITICAL
    }
}

/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.tim.utility;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.*;
import java.util.Arrays;

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
        createLogFile();
        logSeverity(INFO, "Log Created");
        LogHandler.logNoTime(HASHSPACE);
    }

    /**
     * First checks if there are already 10 log files, if so, deletes the oldest, then creates the new log file
     */
    private void createLogFile() {
        File directory = new File(APPDATA + "\\UKSF-MM");

        File[] logs = directory.listFiles((FileFilter) FileFileFilter.FILE);
        Arrays.sort(logs, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
        if(logs.length > 9) {
            logs[0].delete();
        }
        logFile = new File(directory + "\\MM__" + DATEFORMAT.format(DATE) + ".log");
        try {
            logFile.createNewFile();
        } catch(IOException e) {
            error(e);
        }
        System.out.println(logFile.getAbsolutePath());
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

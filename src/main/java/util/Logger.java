package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private String loggerName;

    public static Logger getLogger(String loggerName){
        return new Logger(loggerName);
    }

    private Logger(String loggerName) {
        this.loggerName = loggerName;
    }

    public void info(String message) {
        System.out.printf("\u001b[32m[%s]\u001b[0m %s\t%s\r\n", loggerName, getFormatedDateTime(), message);
    }

    public void warning(String message) {
        System.out.printf("\u001b[31m[%s]\u001b[0m %s\t%s\r\n", loggerName, getFormatedDateTime(), message);
    }

    private static String getFormatedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return now.format(formatter);
    }
}

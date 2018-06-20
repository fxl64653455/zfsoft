package com.zfsoft.boot.metrics.utils.jdbc;

import java.sql.SQLException;

import com.zfsoft.boot.metrics.exception.MetricsException;

/**
 * This specific exception thrown when Metrics encounters a problem in SQL statement.
 */
public class MetricsSqlException extends MetricsException {
    
	/**
     * Creates new instance of FlywaySqlScriptException.
     *
     * @param sqlException Cause of the problem.
     */
    public MetricsSqlException(String message, SQLException sqlException) {
        super(message, sqlException);
    }

    @Override
    public String getMessage() {
        String title = super.getMessage();
        String underline = trimOrPad("", title.length(), '-');

        SQLException cause = (SQLException) getCause();
        while (cause.getNextException() != null) {
            cause = cause.getNextException();
        }

        String message = "\n" + title + "\n" + underline + "\n";
        message += "SQL State  : " + cause.getSQLState() + "\n";
        message += "Error Code : " + cause.getErrorCode() + "\n";
        if (cause.getMessage() != null) {
            message += "Message    : " + cause.getMessage().trim() + "\n";
        }

        return message;
    }
    

    /**
     * Trims or pads this string, so it has this exact length.
     *
     * @param str     The string to adjust. {@code null} is treated as an empty string.
     * @param length  The exact length to reach.
     * @param padChar The padding character.
     * @return The adjusted string.
     */
    public static String trimOrPad(String str, int length, char padChar) {
        String result;
        if (str == null) {
            result = "";
        } else {
            result = str;
        }

        if (result.length() > length) {
            return result.substring(0, length);
        }

        while (result.length() < length) {
            result += padChar;
        }
        return result;
    }
}

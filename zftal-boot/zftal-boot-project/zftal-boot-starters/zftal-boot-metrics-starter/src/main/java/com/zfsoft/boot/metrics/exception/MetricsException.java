package com.zfsoft.boot.metrics.exception;

/**
 * Exception thrown when Metrics encounters a problem.
 */
public class MetricsException extends RuntimeException {
    /**
     * Creates a new MetricsException with this message and this cause.
     *
     * @param message The exception message.
     * @param cause   The exception cause.
     */
    public MetricsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new MetricsException with this cause. For use in subclasses that override getMessage().
     *
     * @param cause   The exception cause.
     */
    public MetricsException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new MetricsException with this message.
     *
     * @param message The exception message.
     */
    public MetricsException(String message) {
        super(message);
    }

    /**
     * Creates a new MetricsException. For use in subclasses that override getMessage().
     */
    public MetricsException() {
        super();
    }
}

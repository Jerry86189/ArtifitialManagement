package com.jerry86189.artifitialmanagement.exceptions;

/**
 * ClassName: DeleteFailed
 * Description: TODO
 * date: 2023/06/10 11:31
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public class DeleteFailed extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DeleteFailed(String message) {
        super(message);
    }
}

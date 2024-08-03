package pl.grzeslowski.jsupla.server.api;

import java.util.concurrent.ExecutionException;

/**
 * Represents a server that manages communication channels.
 * Implementations of this interface should provide methods for creating and managing channels.
 */
public interface Server extends AutoCloseable {

    /**
     * Closes the server and releases any associated resources.
     * After calling this method, the server should no longer be used.
     *
     * @throws ExecutionException   If an exception occurs during server execution.
     * @throws InterruptedException If the thread is interrupted while closing the server.
     */
    @Override
    void close() throws ExecutionException, InterruptedException;
}

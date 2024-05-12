package pl.grzeslowski.jsupla.server.api;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 * Represents a communication channel that allows sending and receiving messages.
 * Implementations of this interface should provide methods for interacting with the channel.
 */
public interface Channel extends AutoCloseable {

    /**
     * Returns a reactive stream of messages from the server to the client.
     *
     * @return A Flux of {@link ToServerProto} messages.
     */
    Flux<ToServerProto> getMessagePipe();

    /**
     * Writes messages from the client to the server.
     *
     * @param fromServerEntityFlux A Flux of {@link FromServerProto} messages to be sent to the server.
     * @return A Flux of {@link LocalDateTime} representing the timestamps of sent messages.
     */
    Flux<LocalDateTime> write(Flux<FromServerProto> fromServerEntityFlux);

    /**
     * Closes the communication channel.
     * After calling this method, the channel should no longer be used.
     */
    @Override
    void close();
}

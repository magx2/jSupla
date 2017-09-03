package pl.grzeslowski.jsupla.server.api.ents.channels;

public interface Channel<T> {
    void write(T data);
}

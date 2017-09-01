package pl.grzeslowski.jsupla.server.ents.channels;

public interface Channel<T> {
    void write(T data);
}

package pl.grzeslowski.jsupla.protocol.encoders;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleDataPacketIdGenerator implements DataPacketIdGenerator {
    private final AtomicInteger id = new AtomicInteger(1);

    @Override
    public int nextId() {
        return id.getAndIncrement();
    }
}
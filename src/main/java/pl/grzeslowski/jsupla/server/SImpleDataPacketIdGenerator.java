package pl.grzeslowski.jsupla.server;

import java.util.concurrent.atomic.AtomicInteger;

public class SImpleDataPacketIdGenerator implements DataPacketIdGenerator {
    private final AtomicInteger id = new AtomicInteger(1);

    @Override
    public int nextId() {
        return id.getAndIncrement();
    }
}

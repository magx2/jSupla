package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.SuplaChannel;
import pl.grzeslowski.jsupla.server.SuplaConnection;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket> implements Publisher<SuplaConnection> {
    private final Logger logger = LoggerFactory.getLogger(SuplaHandler.class);

    private final Map<Subscriber<? super SuplaConnection>, Long> subscribers = new HashMap<>();
    private final ReadWriteLock subscribersLock = new ReentrantReadWriteLock();
    private final ExecutorService executorService;

    private SuplaChannel ctx;

    SuplaHandler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.ctx = ctx::write;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, SuplaDataPacket msg) throws Exception {
        logger.trace("Got {}", msg);

        readLock(s -> s.entrySet()
                              .stream()
                              .filter(entry -> entry.getValue() > 0)
                              .forEach(entry -> {
                                  executorService.submit(() -> entry.getKey().onNext(newSuplaConnection(msg)));
                                  entry.setValue(entry.getValue() - 1);
                              }));
    }

    private SuplaConnection newSuplaConnection(SuplaDataPacket msg) {
        return new SuplaConnection(parseSuplaDataPacket(msg), SuplaHandler.this.ctx);
    }

    private Request parseSuplaDataPacket(final SuplaDataPacket msg) {
        return null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        writeLock(s -> {
            s.forEach((k, v) -> k.onError(cause));
            s.clear();
        });
    }

    @Override
    public void subscribe(final Subscriber<? super SuplaConnection> subscriber) {
        writeLock(s -> s.remove(subscriber));

        subscriber.onSubscribe(new Subscription() {
            @Override
            public void request(final long n) {
                writeLock(s -> {
                    final Long requested = s.getOrDefault(subscriber, 0L);
                    s.put(subscriber, requested + n);
                });
            }

            @Override
            public void cancel() {
                writeLock(s -> s.remove(subscriber));
            }
        });
    }

    private void writeLock(Consumer<Map<Subscriber<? super SuplaConnection>, Long>> consumer) {
        final Lock lock = subscribersLock.writeLock();
        try {
            lock.lock();
            consumer.accept(subscribers);
        } finally {
            lock.unlock();
        }
    }

    private void readLock(Consumer<Map<Subscriber<? super SuplaConnection>, Long>> consumer) {
        final Lock lock = subscribersLock.readLock();
        try {
            lock.lock();
            consumer.accept(subscribers);
        } finally {
            lock.unlock();
        }
    }
}

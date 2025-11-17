package pl.grzeslowski.jsupla.server;

import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;

public class TestMessageHandlerFactory implements MessageHandlerFactory {
    private final Logger logger = LoggerFactory.getLogger(TestMessageHandlerFactory.class);

    @Override
    public MessageHandler create(SocketChannel ch) {
        return new TestMessageHandler();
    }
}

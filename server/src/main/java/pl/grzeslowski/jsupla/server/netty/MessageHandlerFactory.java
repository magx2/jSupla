package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.socket.SocketChannel;
import pl.grzeslowski.jsupla.server.api.MessageHandler;

@FunctionalInterface
public interface MessageHandlerFactory {
    MessageHandler create(SocketChannel ch);
}

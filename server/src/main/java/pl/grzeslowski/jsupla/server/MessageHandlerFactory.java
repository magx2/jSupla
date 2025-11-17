package pl.grzeslowski.jsupla.server;

import io.netty.channel.socket.SocketChannel;

@FunctionalInterface
public interface MessageHandlerFactory {
    MessageHandler create(SocketChannel ch);
}

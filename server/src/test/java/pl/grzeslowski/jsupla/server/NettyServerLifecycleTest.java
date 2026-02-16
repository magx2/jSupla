package pl.grzeslowski.jsupla.server;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.ServerSocket;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

class NettyServerLifecycleTest {
    private static final MessageHandlerFactory NOOP_HANDLER_FACTORY =
            socketChannel ->
                    new MessageHandler() {
                        @Override
                        public void handle(ToServerProto toServerProto) {}

                        @Override
                        public void active(SuplaWriter writer) {}

                        @Override
                        public void inactive() {}

                        @Override
                        public void socketException(Throwable exception) {}
                    };

    @Test
    void closeShouldBeIdempotent() throws Exception {
        try (var server =
                new NettyServer(new NettyConfig(NettyConfig.RANDOM_PORT), NOOP_HANDLER_FACTORY)) {
            assertThatCode(
                            () -> {
                                server.close();
                                server.close();
                            })
                    .doesNotThrowAnyException();
        }
    }

    @Test
    void constructorShouldFailFastWhenPortIsAlreadyOccupied() throws Exception {
        try (var occupiedSocket = new ServerSocket(0)) {
            var occupiedPort = occupiedSocket.getLocalPort();

            assertThatThrownBy(
                            () ->
                                    new NettyServer(
                                            new NettyConfig(occupiedPort), NOOP_HANDLER_FACTORY))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("Cannot bind server to port " + occupiedPort);
        }
    }
}

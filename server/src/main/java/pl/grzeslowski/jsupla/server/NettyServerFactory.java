package pl.grzeslowski.jsupla.server;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;

@RequiredArgsConstructor
public final class NettyServerFactory {
    @NonNull private final CallTypeParser callTypeParser;
    @NonNull private final DecoderFactory decoderFactory;
    @NonNull private final EncoderFactory encoderFactory;

    public NettyServer createNewServer(
            @NonNull NettyConfig nettyConfig,
            @NonNull MessageHandlerFactory messageHandlerFactory) {
        return new NettyServer(
                nettyConfig, callTypeParser, decoderFactory, encoderFactory, messageHandlerFactory);
    }
}

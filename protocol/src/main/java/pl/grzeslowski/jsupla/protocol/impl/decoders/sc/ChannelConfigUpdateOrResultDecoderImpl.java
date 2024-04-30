package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ChannelConfigUpdateOrResult;
import pl.grzeslowski.jsupla.protocol.api.structs.scs.ChannelConfigDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

@lombok.RequiredArgsConstructor
@javax.annotation.Generated(value = "Generated from TSC_ChannelConfigUpdateOrResult -> proto.h (supla core)", date = "2024-04-29T15:12:18.050+02:00[Europe/Belgrade]")
public final class ChannelConfigUpdateOrResultDecoderImpl implements Decoder<ChannelConfigUpdateOrResult> {
    public static final ChannelConfigUpdateOrResultDecoderImpl INSTANCE = new ChannelConfigUpdateOrResultDecoderImpl(PrimitiveDecoderImpl.INSTANCE, ChannelConfigDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;
    private final ChannelConfigDecoderImpl channelConfigDecoder;

    @Override
    public ChannelConfigUpdateOrResult decode(byte[] bytes, int offset) {

        val result = primitiveDecoder.parseUnsignedByte(bytes, offset);
        ;
        offset += BYTE_SIZE;

        val config = channelConfigDecoder.decode(bytes, offset);
        ;
        offset += config.size();

        return new ChannelConfigUpdateOrResult(result, config);
    }
}

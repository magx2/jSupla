package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelC;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.*;

@lombok.RequiredArgsConstructor
@javax.annotation.Generated(value = "Generated from TSC_SuplaChannel_C -> proto.h (supla core)", date = "2024-04-29T15:12:18.063+02:00[Europe/Belgrade]")
public final class SuplaChannelCDecoderImpl implements Decoder<SuplaChannelC> {
    public static final SuplaChannelCDecoderImpl INSTANCE = new SuplaChannelCDecoderImpl(PrimitiveDecoderImpl.INSTANCE, pl.grzeslowski.jsupla.protocol.impl.decoders.SuplaChannelValueDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;

    @Override
    public SuplaChannelC decode(byte[] bytes, int offset) {

        val eOL = primitiveDecoder.parseChar(bytes, offset);
        ;
        offset += BYTE_SIZE;

        val id = primitiveDecoder.parseInt(bytes, offset);
        ;
        offset += INT_SIZE;

        val deviceId = primitiveDecoder.parseInt(bytes, offset);
        ;
        offset += INT_SIZE;

        val locationId = primitiveDecoder.parseInt(bytes, offset);
        ;
        offset += INT_SIZE;

        val type = primitiveDecoder.parseInt(bytes, offset);
        ;
        offset += INT_SIZE;

        val func = primitiveDecoder.parseInt(bytes, offset);
        ;
        offset += INT_SIZE;

        val altIcon = primitiveDecoder.parseInt(bytes, offset);
        ;
        offset += INT_SIZE;

        val userIcon = primitiveDecoder.parseInt(bytes, offset);
        ;
        offset += INT_SIZE;

        val manufacturerId = primitiveDecoder.parseShort(bytes, offset);
        ;
        offset += SHORT_SIZE;

        val productId = primitiveDecoder.parseShort(bytes, offset);
        ;
        offset += SHORT_SIZE;

        val flags = primitiveDecoder.parseUnsignedLong(bytes, offset);
        ;
        offset += INT_SIZE;

        val protocolVersion = primitiveDecoder.parseUnsignedByte(bytes, offset);
        ;
        offset += BYTE_SIZE;

        val online = primitiveDecoder.parseChar(bytes, offset);
        ;
        offset += BYTE_SIZE;

        val value = suplaChannelValueDecoder.decode(bytes, offset);
        ;
        offset += value.size();

        val captionSize = primitiveDecoder.parseUnsignedLong(bytes, offset);
        ;
        offset += INT_SIZE;

        val caption = new char[(int) captionSize];
        for (int i = 0; i < (int) captionSize; i++) {
            caption[i] = primitiveDecoder.parseChar(bytes, offset);
            ;
        }
        offset += (int) captionSize * BYTE_SIZE;

        return new SuplaChannelC(eOL, id, deviceId, locationId, type, func, altIcon, userIcon, manufacturerId, productId, flags, protocolVersion, online, value, captionSize, caption);
    }
}

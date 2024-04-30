package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import lombok.RequiredArgsConstructor;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.DeviceServerDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelExtendedValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.SuplaChannelExtendedValueDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

@RequiredArgsConstructor
public class SuplaDeviceChannelExtendedValueDecoderImpl implements DeviceServerDecoder<SuplaDeviceChannelExtendedValue> {
    public static final SuplaDeviceChannelExtendedValueDecoderImpl INSTANCE = new SuplaDeviceChannelExtendedValueDecoderImpl();
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaChannelExtendedValueDecoderImpl suplaChannelExtendedValueDecoder;

    public SuplaDeviceChannelExtendedValueDecoderImpl() {
        this(PrimitiveDecoderImpl.INSTANCE, SuplaChannelExtendedValueDecoderImpl.INSTANCE);
    }

    @Override
    public SuplaDeviceChannelExtendedValue decode(byte[] bytes, int offset) {
        short channelNumber = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val value = suplaChannelExtendedValueDecoder.decode(bytes, offset);

        return new SuplaDeviceChannelExtendedValue(channelNumber, value);
    }
}

package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.DeviceConfigUpdateOrResult;
import pl.grzeslowski.jsupla.protocol.api.structs.scs.DeviceConfigDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

@lombok.RequiredArgsConstructor
@javax.annotation.Generated(value = "Generated from TSC_DeviceConfigUpdateOrResult -> proto.h (supla core)", date = "2024-04-29T15:12:18.051+02:00[Europe/Belgrade]")
public final class DeviceConfigUpdateOrResultDecoderImpl implements Decoder<DeviceConfigUpdateOrResult> {
    public static final DeviceConfigUpdateOrResultDecoderImpl INSTANCE = new DeviceConfigUpdateOrResultDecoderImpl(PrimitiveDecoderImpl.INSTANCE, DeviceConfigDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;
    private final DeviceConfigDecoderImpl deviceConfigDecoder;

    @Override
    public DeviceConfigUpdateOrResult decode(byte[] bytes, int offset) {

        val result = primitiveDecoder.parseUnsignedByte(bytes, offset);
        ;
        offset += BYTE_SIZE;

        val config = deviceConfigDecoder.decode(bytes, offset);
        ;
        offset += config.size();

        return new DeviceConfigUpdateOrResult(result, config);
    }
}

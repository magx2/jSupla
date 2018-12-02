package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaRegisterClientResultBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public class SuplaRegisterClientResultBDecoderImpl implements SuplaRegisterClientResultBDecoder {
    public static final SuplaRegisterClientResultBDecoderImpl INSTANCE = new SuplaRegisterClientResultBDecoderImpl();
    
    @Override
    public SuplaRegisterClientResultB decode(byte[] bytes, int offset) {

        final int resultCode = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int clientId = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationCount = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelCount = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int flags = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;


        final short activityTimeout = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short version = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short versionMin = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);

        return new SuplaRegisterClientResultB(
                resultCode,
                clientId,
                locationCount,
                channelCount,
                flags,
                activityTimeout,
                version,
                versionMin
        );
    }
}

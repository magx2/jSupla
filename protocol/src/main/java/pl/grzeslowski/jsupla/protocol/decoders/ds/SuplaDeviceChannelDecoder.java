package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannel;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
public class SuplaDeviceChannelDecoder implements DeviceServerDecoder<SuplaDeviceChannel> {
    @Override
    public SuplaDeviceChannel decode(byte[] bytes, int offset) {
        final short number = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int type = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaDeviceChannel(number, type, value);
    }
}
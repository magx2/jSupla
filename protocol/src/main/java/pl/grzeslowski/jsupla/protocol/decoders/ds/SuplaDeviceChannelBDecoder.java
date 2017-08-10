package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelB;

import static java.util.Arrays.copyOfRange;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser.parseInt;
import static pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser.parseUnsignedByte;

public class SuplaDeviceChannelBDecoder implements DeviceServerDecoder<SuplaDeviceChannelB> {
    @Override
    public SuplaDeviceChannelB decode(byte[] bytes, int offset) {
        final short number = parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int type = parseInt(bytes, offset);
        offset += INT_SIZE;

        final int funcList = parseInt(bytes, offset);
        offset += INT_SIZE;

        final int defaultField = parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        offset += SUPLA_CHANNELVALUE_SIZE;

        return new SuplaDeviceChannelB(number, type, funcList, defaultField, value);
    }
}

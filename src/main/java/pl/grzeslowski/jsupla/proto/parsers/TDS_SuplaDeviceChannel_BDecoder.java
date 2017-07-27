package pl.grzeslowski.jsupla.proto.parsers;

import pl.grzeslowski.jsupla.proto.structs.TDS_SuplaDeviceChannel_B;

import static java.util.Arrays.copyOfRange;
import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.proto.parsers.PrimitiveParser.parseInt;
import static pl.grzeslowski.jsupla.proto.parsers.PrimitiveParser.parseUnsignedByte;

public class TDS_SuplaDeviceChannel_BDecoder implements Decoder<TDS_SuplaDeviceChannel_B> {
    @Override
    public TDS_SuplaDeviceChannel_B parse(byte[] bytes, int offset) {
        short number = parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        int type = parseInt(bytes, offset);
        offset += INT_SIZE;

        int funcList = parseInt(bytes, offset);
        offset += INT_SIZE;


        int default_ = parseInt(bytes, offset);
        offset += INT_SIZE;

        byte[] value = copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        offset += SUPLA_CHANNELVALUE_SIZE;


        if (offset != bytes.length) { // TODO remove
            throw new RuntimeException(offset + " " + bytes.length);
        }
        return new TDS_SuplaDeviceChannel_B(number, type, funcList, default_, value);
    }
}

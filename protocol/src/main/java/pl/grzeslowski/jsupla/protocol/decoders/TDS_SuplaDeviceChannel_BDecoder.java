package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.structs.ds.TDS_SuplaDeviceChannel_B;

import static java.util.Arrays.copyOfRange;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser.parseInt;
import static pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser.parseUnsignedByte;

public class TDS_SuplaDeviceChannel_BDecoder implements Decoder<TDS_SuplaDeviceChannel_B> {
    @Override
    public TDS_SuplaDeviceChannel_B decode(TSuplaDataPacket dataPacket) {
        byte[] bytes = dataPacket.data;
        int offset = 0;
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

        return new TDS_SuplaDeviceChannel_B(number, type, funcList, default_, value);
    }
}

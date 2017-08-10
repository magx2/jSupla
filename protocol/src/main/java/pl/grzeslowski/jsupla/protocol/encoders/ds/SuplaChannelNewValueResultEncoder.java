package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaChannelNewValueResult;

public class SuplaChannelNewValueResultEncoder implements DeviceServerEncoder<SuplaChannelNewValueResult> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelNewValueResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeUnsignedByte(proto.channelNumber, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.senderId, data, offset);
        offset += PrimitiveEncoder.writeByte(proto.success, data, offset);

        return data;
    }
}

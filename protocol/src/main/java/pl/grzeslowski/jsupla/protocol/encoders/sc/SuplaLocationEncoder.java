package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocation;

public class SuplaLocationEncoder implements ServerClientEncoder<SuplaLocation> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaLocation proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeByte(proto.eol, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.id, data, offset);
        offset += PrimitiveEncoder.writeUnsignedInteger(proto.captionSize, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.caption, data, offset);

        return data;
    }
}

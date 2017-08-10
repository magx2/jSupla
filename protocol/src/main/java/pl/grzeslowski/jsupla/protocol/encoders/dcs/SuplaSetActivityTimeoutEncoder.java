package pl.grzeslowski.jsupla.protocol.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.dcs.SuplaSetActivityTimeout;

public final class SuplaSetActivityTimeoutEncoder implements DeviceClientServerEncoder<SuplaSetActivityTimeout> {
    @Override
    public byte[] encode(SuplaSetActivityTimeout proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        PrimitiveEncoder.writeUnsignedByte(proto.activityTimeout, data, offset);

        return data;
    }
}

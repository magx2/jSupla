package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.DataPacketIdGenerator;
import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder.writeInteger;

public class TSD_SuplaRegisterDeviceResultEncoder implements Encoder<SuplaRegisterDeviceResult> {
    private final int version;
    private final DataPacketIdGenerator idGenerator;

    public TSD_SuplaRegisterDeviceResultEncoder(int version, DataPacketIdGenerator idGenerator) {
        this.version = min(version, 1);
        this.idGenerator = requireNonNull(idGenerator);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public SuplaDataPacket encode(SuplaRegisterDeviceResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += writeInteger(proto.resultCode, data, offset);
        offset += PrimitiveEncoder.writeByte(proto.activityTimeout, data, offset);
        offset += PrimitiveEncoder.writeByte(proto.version, data, offset);
        offset += PrimitiveEncoder.writeByte(proto.versionMin, data, offset);

        return new SuplaDataPacket(
                (short) version,
                idGenerator.nextId(),
                proto.callType().getValue(),
                data.length,
                data);
    }
}

package pl.grzeslowski.jsupla.proto.encoders;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.proto.structs.sd.TSD_SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.server.DataPacketIdGenerator;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.proto.encoders.PrimitiveEncoder.writeInteger;

public class TSD_SuplaRegisterDeviceResultEncoder implements Encoder<TSD_SuplaRegisterDeviceResult> {
    private final int version;
    private final DataPacketIdGenerator idGenerator;

    public TSD_SuplaRegisterDeviceResultEncoder(int version, DataPacketIdGenerator idGenerator) {
        this.version = min(version, 1);
        this.idGenerator = requireNonNull(idGenerator);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public TSuplaDataPacket encode(TSD_SuplaRegisterDeviceResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += writeInteger(proto.resultCode, data, offset);
        offset += PrimitiveEncoder.writeByte(proto.activityTimeout, data, offset);
        offset += PrimitiveEncoder.writeByte(proto.version, data, offset);
        offset += PrimitiveEncoder.writeByte(proto.versionMin, data, offset);

        return new TSuplaDataPacket((short) version, idGenerator.nextId(), proto.callType().getValue(), data.length, data);
    }
}

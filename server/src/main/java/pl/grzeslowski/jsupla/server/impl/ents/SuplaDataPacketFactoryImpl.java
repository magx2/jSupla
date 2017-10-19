package pl.grzeslowski.jsupla.server.impl.ents;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.api.ents.SuplaDataPacketFactory;

import static pl.grzeslowski.jsupla.Preconditions.size;

public class SuplaDataPacketFactoryImpl implements SuplaDataPacketFactory {
    private short version;

    public SuplaDataPacketFactoryImpl(final int version) {
        this.version = (short) size(version, Short.MIN_VALUE, Short.MAX_VALUE);
        ;
    }

    @Override
    public SuplaDataPacket newSuplaDataPacketFactory(final byte[] bytes, final CallType callType) {
        return new SuplaDataPacket(version, 1, callType.getValue(), bytes.length, bytes);
    }

}
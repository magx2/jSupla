package pl.grzeslowski.jsupla.server.reactor.map;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

import java.util.function.Function;

public final class SuplaDataPackageToToServer<InT extends SuplaDataPacket, OutT extends ToServerProto> implements Function<InT, OutT> {
    @Override
    public OutT apply(final InT inT) {
        return null;
    }
}

package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.entities.requests.RegisterDeviceRequestB;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

import static java.lang.String.format;

public class ParsersFactoryImpl implements ParsersFactory {
    private final Parser<RegisterDeviceRequestB, SuplaRegisterDeviceB> deviceRegisterRequestBParser =
            new DeviceRegisterRequestBParser();

    @SuppressWarnings("unchecked")
    @Override
    public <ProtoT extends ProtoWithSize> Parser<? extends Request, ProtoT> getParser(ProtoT proto) {
        if (proto instanceof SuplaRegisterDeviceB) {
            return (Parser<? extends Request, ProtoT>) deviceRegisterRequestBParser;
        } else if (proto instanceof SuplaSetActivityTimeout) {
            return (Parser<? extends Request, ProtoT>) new ActivityTimeoutRequestParser();
        }

        throw new IllegalArgumentException(format("Don't know which parser suits for DeviceServer class %s!",
                proto.getClass().getSimpleName()));
    }
}

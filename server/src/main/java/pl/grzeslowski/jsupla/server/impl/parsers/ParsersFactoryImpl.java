package pl.grzeslowski.jsupla.server.impl.parsers;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.api.entities.requests.Request;
import pl.grzeslowski.jsupla.server.api.entities.requests.ds.RegisterDeviceRequestB;
import pl.grzeslowski.jsupla.server.api.parsers.Parser;
import pl.grzeslowski.jsupla.server.api.parsers.ParsersFactory;

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

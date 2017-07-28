package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.proto.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.proto.structs.ds.TDS_SuplaRegisterDevice_B;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequestB;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

import static java.lang.String.format;

public class ParsersFactoryImpl implements ParsersFactory {
    private final Parser<DeviceRegisterRequestB, TDS_SuplaRegisterDevice_B> deviceRegisterRequestBParser = new DeviceRegisterRequestBParser();

    @SuppressWarnings("unchecked")
    @Override
    public <DS extends DeviceServer> Parser<? extends Response, DS> getParser(DS ds) {
        if (ds instanceof TDS_SuplaRegisterDevice_B) {
            return (Parser<? extends Response, DS>) deviceRegisterRequestBParser;
        }

        throw new IllegalArgumentException(format("Don't know which parser suits for DeviceServer class %s!", ds.getClass().getSimpleName()));
    }
}

package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequestB;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

import static java.lang.String.format;

public class ParsersFactoryImpl implements ParsersFactory {
    private final Parser<DeviceRegisterRequestB, SuplaRegisterDeviceB> deviceRegisterRequestBParser = new DeviceRegisterRequestBParser();

    @SuppressWarnings("unchecked")
    @Override
    public <DS extends DeviceServer> Parser<? extends Request, DS> getParser(DS ds) {
        if (ds instanceof SuplaRegisterDeviceB) {
            return (Parser<? extends Request, DS>) deviceRegisterRequestBParser;
        }

        throw new IllegalArgumentException(format("Don't know which parser suits for DeviceServer class %s!", ds.getClass().getSimpleName()));
    }
}

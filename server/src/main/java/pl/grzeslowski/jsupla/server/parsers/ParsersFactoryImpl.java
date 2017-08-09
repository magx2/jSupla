package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequestB;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

import static java.lang.String.format;

public class ParsersFactoryImpl implements ParsersFactory {
    private final Parser<DeviceRegisterRequestB, SuplaRegisterDeviceB> deviceRegisterRequestBParser =
            new DeviceRegisterRequestBParser();

    @SuppressWarnings("unchecked")
    @Override
    public <DeviceServerT extends DeviceServer> Parser<? extends Request, DeviceServerT> getParser(
            DeviceServerT deviceServer) {
        if (deviceServer instanceof SuplaRegisterDeviceB) {
            return (Parser<? extends Request, DeviceServerT>) deviceRegisterRequestBParser;
        }

        throw new IllegalArgumentException(format("Don't know which parser suits for DeviceServer class %s!",
                deviceServer.getClass().getSimpleName()));
    }
}

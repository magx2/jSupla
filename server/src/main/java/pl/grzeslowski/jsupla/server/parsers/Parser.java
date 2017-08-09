package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

public interface Parser<RequestT extends Request, DeviceServerT extends DeviceServer> {
    RequestT parse(DeviceServerT proto);
}

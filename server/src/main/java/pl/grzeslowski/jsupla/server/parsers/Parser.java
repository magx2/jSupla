package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

public interface Parser<Rq extends Request, DS extends DeviceServer> {
    Rq parse(DS proto);
}

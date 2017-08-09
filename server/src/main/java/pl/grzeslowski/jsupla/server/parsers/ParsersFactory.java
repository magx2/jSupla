package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

public interface ParsersFactory {
    <DS extends DeviceServer> Parser<? extends Request, DS> getParser(DS ds);
}

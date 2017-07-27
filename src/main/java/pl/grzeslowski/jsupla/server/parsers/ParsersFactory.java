package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.proto.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface ParsersFactory {
    <DS extends DeviceServer> Parser<? extends Response, DS> getParser(DS ds);
}

package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.ClientPingRequest;
import pl.grzeslowski.jsupla.server.entities.responses.ServerPingResponse;

public interface PingListener {
    ServerPingResponse onPing(ClientPingRequest clientPingRequest);
}

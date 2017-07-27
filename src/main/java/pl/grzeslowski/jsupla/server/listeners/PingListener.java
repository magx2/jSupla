package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.ClientPingRequest;
import pl.grzeslowski.jsupla.server.entities.responses.ServerPing;

public interface PingListener {
    ServerPing onPing(ClientPingRequest clientPingRequest);
}

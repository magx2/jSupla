package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.ClientPing;
import pl.grzeslowski.jsupla.server.entities.ServerPing;

public interface PingListener {
    ServerPing onPing(ClientPing clientPing);
}

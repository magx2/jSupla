package pl.grzeslowski.jsupla.server.logic;

import pl.grzeslowski.jsupla.server.entities.ClientPing;
import pl.grzeslowski.jsupla.server.entities.ServerPing;

public interface PingListener {
    ServerPing onPing(ClientPing clientPing);
}

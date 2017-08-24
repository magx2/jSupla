package pl.grzeslowski.jsupla.server.netty;

import pl.grzeslowski.jsupla.server.SuplaNewConnection;

interface NotificationAboutNewChannel {
    void notify(SuplaNewConnection conn);
}

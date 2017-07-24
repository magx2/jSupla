package pl.grzeslowski.jsupla.server.entities;


import static pl.grzeslowski.jsupla.server.entities.Entity.Version.A;

public interface Entity {
    default Entity.Version version() {
        return A;
    }

    enum Version {
        A, B, C
    }
}

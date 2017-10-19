package pl.grzeslowski.jsupla.protocoljava.api.types;

import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.A;

public interface Entity {
    default Version version() {
        return A;
    }

    enum Version {
        A, B, C
    }
}

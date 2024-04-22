package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class SuplaRegisterClientCRandomizer implements Randomizer<SuplaRegisterClientC> {
    private final RandomSupla randomSupla;

    public SuplaRegisterClientCRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterClientC getRandomValue() {
        return new SuplaRegisterClientC(
            randomSupla.nextByteArrayFromString(SUPLA_EMAIL_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_AUTHKEY_SIZE),
            randomSupla.nextByteArrayFromString(SUPLA_GUID_SIZE),
            randomSupla.nextByteArrayFromString(SUPLA_CLIENT_NAME_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_SOFTVER_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_SERVER_NAME_MAXSIZE)
        );
    }
}

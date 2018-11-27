package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_AUTHKEY_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_EMAIL_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaRegisterClientCRandomizer implements Randomizer<SuplaRegisterClientC> {
    private final RandomSupla randomSupla;

    public SuplaRegisterClientCRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterClientC getRandomValue() {
        return new SuplaRegisterClientC(
                randomSupla.nextByteArray(SUPLA_EMAIL_MAXSIZE),
                randomSupla.nextByteArray(SUPLA_AUTHKEY_SIZE),
                randomSupla.nextByteArray(SUPLA_GUID_SIZE),
                randomSupla.nextByteArray(SUPLA_CLIENT_NAME_MAXSIZE),
                randomSupla.nextByteArray(SUPLA_SOFTVER_MAXSIZE),
                randomSupla.nextByteArray(SUPLA_SERVER_NAME_MAXSIZE)
        );
    }
}

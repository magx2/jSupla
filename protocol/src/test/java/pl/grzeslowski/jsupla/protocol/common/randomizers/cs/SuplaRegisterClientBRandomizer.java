package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaRegisterClientBRandomizer implements Randomizer<SuplaRegisterClientB> {
    private final RandomSupla randomSupla;

    public SuplaRegisterClientBRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterClientB getRandomValue() {
        return new SuplaRegisterClientB(
                                               randomSupla.nextPositiveInt(),
                                               randomSupla.nextByteArray(SUPLA_ACCESSID_PWD_MAXSIZE),
                                               randomSupla.nextByteArray(SUPLA_GUID_SIZE),
                                               randomSupla.nextByteArray(SUPLA_CLIENT_NAME_MAXSIZE),
                                               randomSupla.nextByteArray(SUPLA_SOFTVER_MAXSIZE),
                                               randomSupla.nextByteArray(SUPLA_SERVER_NAME_MAXSIZE)
        );
    }
}

package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaRegisterClientRandomizer implements Randomizer<SuplaRegisterClient> {
    private final RandomSupla randomSupla;

    public SuplaRegisterClientRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterClient getRandomValue() {
        return new SuplaRegisterClient(
                randomSupla.nextPositiveInt(),
                randomSupla.nextByteArrayFromString(SUPLA_ACCESSID_PWD_MAXSIZE),
                randomSupla.nextByteArrayFromString(SUPLA_GUID_SIZE),
                randomSupla.nextByteArrayFromString(SUPLA_CLIENT_NAME_MAXSIZE),
                randomSupla.nextByteArrayFromString(SUPLA_SOFTVER_MAXSIZE));
    }
}

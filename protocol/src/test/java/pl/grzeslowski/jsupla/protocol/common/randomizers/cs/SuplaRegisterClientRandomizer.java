package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaRegisterClientRandomizer implements Randomizer<SuplaRegisterClient> {
    private final RandomBean randomBean;

    public SuplaRegisterClientRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaRegisterClient getRandomValue() {
        return new SuplaRegisterClient(
                                              randomBean.nextPositiveInt(),
                                              randomBean.nextByteArray(SUPLA_ACCESSID_PWD_MAXSIZE),
                                              randomBean.nextByteArray(SUPLA_GUID_SIZE),
                                              randomBean.nextByteArray(SUPLA_CLIENT_NAME_MAXSIZE),
                                              randomBean.nextByteArray(SUPLA_SOFTVER_MAXSIZE)
        );
    }
}

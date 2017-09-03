package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaRegisterClientBRandomizer implements Randomizer<SuplaRegisterClientB> {
    private final RandomBean randomBean;

    public SuplaRegisterClientBRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaRegisterClientB getRandomValue() {
        return new SuplaRegisterClientB(
                                               randomBean.nextPositiveInt(),
                                               randomBean.nextByteArray(SUPLA_ACCESSID_PWD_MAXSIZE),
                                               randomBean.nextByteArray(SUPLA_GUID_SIZE),
                                               randomBean.nextByteArray(SUPLA_CLIENT_NAME_MAXSIZE),
                                               randomBean.nextByteArray(SUPLA_SOFTVER_MAXSIZE),
                                               randomBean.nextByteArray(SUPLA_SERVER_NAME_MAXSIZE)
        );
    }
}

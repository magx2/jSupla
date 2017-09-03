package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public class SuplaEventRandomizer implements Randomizer<SuplaEvent> {
    private final RandomBean randomBean;

    public SuplaEventRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaEvent getRandomValue() {
        final int senderNameSize = randomBean.nextPositiveInt(SUPLA_SENDER_NAME_MAXSIZE);
        return new SuplaEvent(
                                     randomBean.nextPositiveInt(),
                                     randomBean.nextPositiveInt(),
                                     randomBean.nextUnsignedInt(),
                                     randomBean.nextPositiveInt(),
                                     senderNameSize,
                                     randomBean.nextByteArray(senderNameSize)
        );
    }
}

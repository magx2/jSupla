package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaEventRandomizer implements Randomizer<SuplaEvent> {
    private final RandomBean randomBean;

    public SuplaEventRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaEvent getRandomValue() {
        final int senderNameSize = randomBean.nextInt();
        return new SuplaEvent(
                                     randomBean.nextInt(),
                                     randomBean.nextInt(),
                                     randomBean.nextUnsignedInt(),
                                     randomBean.nextInt(),
                                     senderNameSize,
                                     randomBean.nextByteArray(senderNameSize)
        );
    }
}

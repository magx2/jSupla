package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public class SuplaEventRandomizer implements Randomizer<SuplaEvent> {
    private final RandomSupla randomSupla;

    public SuplaEventRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaEvent getRandomValue() {
        byte[] senderName = randomSupla.nextByteArrayWithoutZerosOnEnd(SUPLA_SENDER_NAME_MAXSIZE);
        return new SuplaEvent(
                                     randomSupla.nextPositiveInt(),
                                     randomSupla.nextPositiveInt(),
                                     randomSupla.nextUnsignedInt(),
                                     randomSupla.nextPositiveInt(),
                senderName.length,
                senderName
        );
    }
}

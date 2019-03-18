package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;

public class SuplaEventRandomizer implements Randomizer<SuplaEvent> {
    private final RandomSupla randomSupla;

    public SuplaEventRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaEvent getRandomValue() {
        byte[] senderName = findSenderNameWithProperSize();
        return new SuplaEvent(
                                     randomSupla.nextPositiveInt(),
                                     randomSupla.nextPositiveInt(),
                                     randomSupla.nextUnsignedInt(),
                                     randomSupla.nextPositiveInt(),
                senderName.length,
                senderName
        );
    }

    private byte[] findSenderNameWithProperSize() {
        byte[] caption = randomSupla.nextByteArrayFromString(SUPLA_LOCATION_CAPTION_MAXSIZE);
        int end = caption.length;
        for (int i = 0; i < caption.length; i++) {
            if (caption[i] == (byte) 0) {
                end = i;
                break;
            }
        }
        return Arrays.copyOf(caption, end);
    }
}

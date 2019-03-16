package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;

public class SuplaLocationRandomizer implements Randomizer<SuplaLocation> {
    private final RandomSupla randomSupla;

    public SuplaLocationRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaLocation getRandomValue() {
        byte[] captionWithProperSize = findCaptionWithProperSize();
        return new SuplaLocation(
                                        randomSupla.nextByte(),
                                        randomSupla.nextPositiveInt(),
                captionWithProperSize.length,
                captionWithProperSize
        );
    }

    private byte[] findCaptionWithProperSize() {
        byte[] caption = randomSupla.nextByteArrayWithoutZerosOnEnd(SUPLA_LOCATION_CAPTION_MAXSIZE);
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

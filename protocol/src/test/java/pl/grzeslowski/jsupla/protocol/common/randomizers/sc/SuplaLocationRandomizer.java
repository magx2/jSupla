package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;

public class SuplaLocationRandomizer implements Randomizer<SuplaLocation> {
    private final RandomSupla randomSupla;

    public SuplaLocationRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaLocation getRandomValue() {
        final long captionSize = randomSupla.nextUnsignedInt(SUPLA_LOCATION_CAPTION_MAXSIZE);
        return new SuplaLocation(
                                        randomSupla.nextByte(),
                                        randomSupla.nextPositiveInt(),
                                        captionSize,
                                        randomSupla.nextByteArray((int) captionSize)
        );
    }
}

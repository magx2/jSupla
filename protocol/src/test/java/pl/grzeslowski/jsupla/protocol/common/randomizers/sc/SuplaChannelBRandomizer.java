package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

public class SuplaChannelBRandomizer implements Randomizer<SuplaChannelB> {
    private final RandomSupla randomSupla;

    public SuplaChannelBRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelB getRandomValue() {
        final long captionSize = randomSupla.nextUnsignedInt(SUPLA_CHANNEL_CAPTION_MAXSIZE);
        return new SuplaChannelB(
                randomSupla.nextByte(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextInt(),
                randomSupla.nextUnsignedInt(),
                randomSupla.nextUnsignedByte(),
                randomSupla.nextByte(),
                randomSupla.nextObject(
                        pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class),
                captionSize,
                randomSupla.nextByteArray((int) captionSize)
        );
    }
}

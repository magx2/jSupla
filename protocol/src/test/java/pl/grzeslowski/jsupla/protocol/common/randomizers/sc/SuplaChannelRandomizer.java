package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelRandomizer implements Randomizer<SuplaChannel> {
    private final RandomSupla randomSupla;

    public SuplaChannelRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannel getRandomValue() {
        final long captionSize = randomSupla.nextUnsignedInt(SUPLA_CHANNEL_CAPTION_MAXSIZE);
        return new SuplaChannel(
                randomSupla.nextByte(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextByte(),
                randomSupla.nextObject(
                        pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class),
                captionSize,
                randomSupla.nextByteArray((int) captionSize));
    }
}

package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SuplaChannelGroupRandomizer implements Randomizer<SuplaChannelGroup> {
    private final RandomSupla randomSupla;

    public SuplaChannelGroupRandomizer(RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelGroup getRandomValue() {
        byte[] caption = randomSupla.nextString(5).getBytes(UTF_8);
        return new SuplaChannelGroup(
            randomSupla.nextByte(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextInt(),
            randomSupla.nextInt(),
            randomSupla.nextUnsignedInt(),
            caption.length,
            caption
        );
    }
}

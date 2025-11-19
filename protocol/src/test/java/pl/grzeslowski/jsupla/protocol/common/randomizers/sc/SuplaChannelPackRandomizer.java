package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelA;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackA;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelPackRandomizer implements Randomizer<SuplaChannelPack> {
    private final RandomSupla randomSupla;

    public SuplaChannelPackRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelPack getRandomValue() {
        final int count = randomSupla.nextPositiveInt(20);
        final SuplaChannelA[] channels =
                randomSupla
                        .objects(SuplaChannel.class, count)
                        .map(c -> (SuplaChannelA) c)
                        .toArray(SuplaChannelA[]::new);
        return new SuplaChannelPackA(count, randomSupla.nextPositiveInt(), channels);
    }
}

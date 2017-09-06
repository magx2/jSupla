package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static java.util.stream.Collectors.toList;

public class SuplaChannelPackRandomizer implements Randomizer<SuplaChannelPack> {
    private final RandomSupla randomSupla;

    public SuplaChannelPackRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelPack getRandomValue() {
        final int count = randomSupla.nextPositiveInt(20);
        final SuplaChannel[] channels = randomSupla.objects(SuplaChannel.class, count)
                                                .collect(toList())
                                                .toArray(new SuplaChannel[0]);
        return new SuplaChannelPack(
                                           count,
                                           randomSupla.nextPositiveInt(),
                                           channels
        );
    }
}

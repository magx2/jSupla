package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import java.util.stream.Stream;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_PACK_MAXCOUNT;

public class SuplaChannelValuePackRandomizer implements Randomizer<SuplaChannelValuePack> {
    private final RandomSupla randomSupla;

    public SuplaChannelValuePackRandomizer(RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelValuePack getRandomValue() {
        int count = randomSupla.nextPositiveInt(SUPLA_CHANNELVALUE_PACK_MAXCOUNT);
        SuplaChannelValue[] items = Stream.generate(() -> randomSupla.nextObject(SuplaChannelValue.class))
            .limit(count)
            .toArray(SuplaChannelValue[]::new);
        return new SuplaChannelValuePack(
            count,
            randomSupla.nextPositiveInt(),
            items
        );
    }
}

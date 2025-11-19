package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_PACK_MAXCOUNT;

import io.github.benas.randombeans.api.Randomizer;
import java.util.stream.Stream;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValueA;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePackA;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelValuePackRandomizer implements Randomizer<SuplaChannelValuePack> {
    private final RandomSupla randomSupla;

    public SuplaChannelValuePackRandomizer(RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelValuePack getRandomValue() {
        int count = randomSupla.nextPositiveInt(SUPLA_CHANNELVALUE_PACK_MAXCOUNT);
        SuplaChannelValueA[] items =
                Stream.generate(() -> randomSupla.nextObject(SuplaChannelValue.class))
                        .limit(count)
                        .map(c -> (SuplaChannelValueA) c)
                        .toArray(SuplaChannelValueA[]::new);
        return new SuplaChannelValuePackA(count, randomSupla.nextPositiveInt(), items);
    }
}

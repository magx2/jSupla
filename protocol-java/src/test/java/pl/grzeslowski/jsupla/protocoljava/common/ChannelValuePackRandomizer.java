package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValuePack;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_PACK_MAXCOUNT;

public class ChannelValuePackRandomizer implements Randomizer<ChannelValuePack> {
    private final EnhancedRandom random;

    public ChannelValuePackRandomizer(EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public ChannelValuePack getRandomValue() {
        @Size(max = SUPLA_CHANNELVALUE_PACK_MAXCOUNT)
        List<ChannelValue> items = Stream.generate(() -> random.nextObject(ChannelValue.class))
            .limit(random.nextInt(SUPLA_CHANNELVALUE_PACK_MAXCOUNT))
            .collect(Collectors.toList());
        return new ChannelValuePack(
            random.nextInt(100),
            items
        );
    }
}

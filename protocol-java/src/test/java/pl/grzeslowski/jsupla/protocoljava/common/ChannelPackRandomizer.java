package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;

import java.util.List;
import java.util.stream.Collectors;

class ChannelPackRandomizer implements Randomizer<ChannelPack> {
    private final EnhancedRandom random;

    ChannelPackRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public ChannelPack getRandomValue() {
        final List<Channel> channels = random.objects(Channel.class, 15).collect(Collectors.toList());
        return new ChannelPack(
                                      random.nextInt(100),
                                      channels
        );
    }
}

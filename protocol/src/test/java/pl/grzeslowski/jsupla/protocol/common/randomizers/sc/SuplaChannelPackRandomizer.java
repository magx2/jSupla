package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static java.util.stream.Collectors.toList;

public class SuplaChannelPackRandomizer implements Randomizer<SuplaChannelPack> {
    private final RandomBean randomBean;

    public SuplaChannelPackRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaChannelPack getRandomValue() {
        final int count = randomBean.nextPositiveInt();
        final SuplaChannel[] channels = randomBean.objects(SuplaChannel.class, count)
                                                .collect(toList())
                                                .toArray(new SuplaChannel[0]);
        return new SuplaChannelPack(
                                           count,
                                           randomBean.nextPositiveInt(),
                                           channels
        );
    }
}

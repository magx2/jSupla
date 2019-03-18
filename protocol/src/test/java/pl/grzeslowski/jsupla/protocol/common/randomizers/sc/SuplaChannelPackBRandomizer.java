package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELPACK_MAXSIZE;

public class SuplaChannelPackBRandomizer implements Randomizer<SuplaChannelPackB> {
    private final RandomSupla randomSupla;

    public SuplaChannelPackBRandomizer(RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelPackB getRandomValue() {
        int count = randomSupla.nextPositiveInt(SUPLA_CHANNELPACK_MAXSIZE);
        return new SuplaChannelPackB(
                count,
                randomSupla.nextPositiveInt(),
                randomSupla.objects(SuplaChannelB.class, count).toArray(SuplaChannelB[]::new)
        );
    }
}

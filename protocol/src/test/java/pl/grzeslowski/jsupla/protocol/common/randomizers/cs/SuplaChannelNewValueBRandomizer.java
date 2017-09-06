package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaChannelNewValueBRandomizer implements Randomizer<SuplaChannelNewValueB> {
    private final RandomSupla randomSupla;

    public SuplaChannelNewValueBRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelNewValueB getRandomValue() {
        return new SuplaChannelNewValueB(
                                                randomSupla.nextPositiveInt(),
                                                randomSupla.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}

package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelGroupRelationRandomizer implements Randomizer<SuplaChannelGroupRelation> {
    private final RandomSupla randomSupla;

    public SuplaChannelGroupRelationRandomizer(RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelGroupRelation getRandomValue() {
        return new SuplaChannelGroupRelation(
                randomSupla.nextByte(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveInt());
    }
}

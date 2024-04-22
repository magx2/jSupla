package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelGroupRelationPackRandomizer implements Randomizer<SuplaChannelGroupRelationPack> {
    private final RandomSupla randomSupla;

    public SuplaChannelGroupRelationPackRandomizer(RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelGroupRelationPack getRandomValue() {
        SuplaChannelGroupRelation[] items = randomSupla
            .objects(SuplaChannelGroupRelation.class, randomSupla.nextPositiveInt(5))
            .toArray(SuplaChannelGroupRelation[]::new);
        return new SuplaChannelGroupRelationPack(
            items.length,
            randomSupla.nextPositiveInt(),
            items
        );
    }
}

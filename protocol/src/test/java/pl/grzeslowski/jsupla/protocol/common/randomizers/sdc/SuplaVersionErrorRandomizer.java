package pl.grzeslowski.jsupla.protocol.common.randomizers.sdc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaVersionErrorRandomizer implements Randomizer<SuplaVersionError> {
    private final RandomSupla randomSupla;

    public SuplaVersionErrorRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaVersionError getRandomValue() {
        final short serverVersion = randomSupla.nextPositiveUnsignedByte();
        final short serverVersionMin = randomSupla.nextUnsignedByte(serverVersion);
        return new SuplaVersionError(
                                            serverVersionMin,
                                            serverVersion

        );
    }
}

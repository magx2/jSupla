package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaRegisterClientResultRandomizer implements Randomizer<SuplaRegisterClientResult> {
    private final RandomSupla randomSupla;

    public SuplaRegisterClientResultRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterClientResult getRandomValue() {
        final short version = (short) (randomSupla.nextUnsignedByte() + 2);
        return new SuplaRegisterClientResult(
                                                    randomSupla.nextPositiveInt(),
                                                    randomSupla.nextPositiveInt(),
                                                    randomSupla.nextPositiveInt(),
                                                    randomSupla.nextPositiveInt(),
                                                    randomSupla.nextUnsignedByte(),
                                                    version,
                                                    randomSupla.nextUnsignedByte(version)
        );
    }
}

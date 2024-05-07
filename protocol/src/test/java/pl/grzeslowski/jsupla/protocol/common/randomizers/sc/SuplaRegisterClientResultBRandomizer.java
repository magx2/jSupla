package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaRegisterClientResultBRandomizer implements Randomizer<SuplaRegisterClientResultB> {
    private final RandomSupla randomSupla;

    public SuplaRegisterClientResultBRandomizer(RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterClientResultB getRandomValue() {
        final short version = (short) (randomSupla.nextUnsignedByte() + 2);
        return new SuplaRegisterClientResultB(
            randomSupla.nextPositiveInt(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextInt(),
            randomSupla.nextUnsignedByte(),
            version,
            randomSupla.nextUnsignedByte(version),
            randomSupla.nextUnsignedByte(version)
        );
    }

}

package pl.grzeslowski.jsupla.protocol.common.randomizers;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

public class SuplaDataPacketRandomizer implements Randomizer<SuplaDataPacket> {
    private final RandomSupla randomSupla;

    public SuplaDataPacketRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaDataPacket getRandomValue() {
        final int dataSize = randomSupla.nextPositiveInt(SUPLA_MAX_DATA_SIZE);
        return new SuplaDataPacket(
            randomSupla.nextUnsignedByte(),
            randomSupla.nextUnsignedInt(),
            randomSupla.nextLong(100),
            dataSize,
            randomSupla.nextByteArray(dataSize)
        );
    }
}

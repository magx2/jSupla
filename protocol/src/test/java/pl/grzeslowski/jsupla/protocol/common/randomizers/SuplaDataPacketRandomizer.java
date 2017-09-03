package pl.grzeslowski.jsupla.protocol.common.randomizers;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaDataPacketRandomizer implements Randomizer<SuplaDataPacket> {
    private final RandomBean randomBean;

    public SuplaDataPacketRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaDataPacket getRandomValue() {
        final int dataSize = randomBean.nextPositiveInt();
        return new SuplaDataPacket(
                                          randomBean.nextUnsignedByte(),
                                          randomBean.nextUnsignedInt(),
                                          80,
                                          dataSize,
                                          randomBean.nextByteArray(dataSize)
        );
    }
}

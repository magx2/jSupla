package pl.grzeslowski.jsupla.protocol.common.randomizers;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

public class SuplaDataPacketRandomizer implements Randomizer<SuplaDataPacket> {
    private final RandomBean randomBean;

    public SuplaDataPacketRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaDataPacket getRandomValue() {
        final int dataSize = randomBean.nextPositiveInt(SUPLA_MAX_DATA_SIZE);
        return new SuplaDataPacket(
                                          randomBean.nextUnsignedByte(),
                                          randomBean.nextUnsignedInt(),
                                          80,
                                          dataSize,
                                          randomBean.nextByteArray(dataSize)
        );
    }
}

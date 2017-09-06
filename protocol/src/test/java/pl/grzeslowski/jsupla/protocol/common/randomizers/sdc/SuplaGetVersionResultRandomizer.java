package pl.grzeslowski.jsupla.protocol.common.randomizers.sdc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaGetVersionResultRandomizer implements Randomizer<SuplaGetVersionResult> {
    private final RandomSupla randomSupla;

    public SuplaGetVersionResultRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaGetVersionResult getRandomValue() {
        final short protoVersion = randomSupla.nextUnsignedByte();
        final short protoVersionMin = randomSupla.nextUnsignedByte(protoVersion);
        return new SuplaGetVersionResult(
                                                protoVersionMin,
                                                protoVersion,
                                                randomSupla.nextByteArray(SUPLA_SOFTVER_MAXSIZE)
        );
    }
}

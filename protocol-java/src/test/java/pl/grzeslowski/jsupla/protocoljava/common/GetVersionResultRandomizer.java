package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

class GetVersionResultRandomizer implements Randomizer<GetVersionResult> {
    private final EnhancedRandom random;

    GetVersionResultRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public GetVersionResult getRandomValue() {
        final int protoVersion = random.nextInt(UNSIGNED_BYTE_MAX);
        final int protoVersionMin = random.nextInt(protoVersion);
        return new GetVersionResult(
                                           protoVersionMin,
                                           protoVersion,
                                           generateSoftVer()
        );
    }

    private String generateSoftVer() {
        while (true) {
            final String randomString = random.nextObject(String.class);
            if (randomString.length() >= 1 && randomString.length() <= SUPLA_SOFTVER_MAXSIZE) {
                return randomString;
            }
        }
    }
}

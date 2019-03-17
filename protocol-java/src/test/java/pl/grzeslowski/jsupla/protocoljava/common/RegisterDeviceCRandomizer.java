package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;

class RegisterDeviceCRandomizer implements Randomizer<RegisterDeviceC> {
    private final EnhancedRandom random;

    RegisterDeviceCRandomizer(final EnhancedRandom random) {
        this.random = requireNonNull(random);
    }

    @Override
    public RegisterDeviceC getRandomValue() {
        final RegisterDeviceB registerDevice = random.nextObject(RegisterDeviceB.class);
        return new RegisterDeviceC(
                                          registerDevice.getLocationId(),
                                          registerDevice.getLocationPassword(),
                                          registerDevice.getGuid(),
                                          registerDevice.getName(),
                                          registerDevice.getSoftVer(),
                                          registerDevice.getChannels(),
                                          generateServerName()

        );
    }

    private String generateServerName() {
        while (true) {
            final String string = random.nextObject(String.class);
            if (string.length() >= 1 && string.length() <= SUPLA_SERVER_NAME_MAXSIZE) {
                return string;
            }
        }
    }

}

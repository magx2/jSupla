package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannels;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

class RegisterDeviceRandomizer implements Randomizer<RegisterDevice> {
    private final EnhancedRandom random;

    RegisterDeviceRandomizer(final EnhancedRandom random) {
        this.random = requireNonNull(random);
    }

    @Override
    public RegisterDevice getRandomValue() {
        final DeviceChannels channels = random.nextObject(DeviceChannels.class);
        return new RegisterDevice(
                                         random.nextInt(),
                                         generateLocationPassword(),
                                         generateString(SUPLA_GUID_SIZE),
                                         generateString(SUPLA_DEVICE_NAME_MAXSIZE),
                                         generateString(SUPLA_SOFTVER_MAXSIZE),
                                         channels
        );
    }

    private char[] generateLocationPassword() {
        while (true) {
            final char[] password = random.nextObject(char[].class);
            if (password.length >= 1 && password.length <= SUPLA_LOCATION_PWD_MAXSIZE) {
                return password;
            }
        }
    }

    private String generateString(int length) {
        while (true) {
            final String string = random.nextObject(String.class);
            if (string.length() >= 1 && string.length() <= length) {
                return string;
            }
        }
    }

}

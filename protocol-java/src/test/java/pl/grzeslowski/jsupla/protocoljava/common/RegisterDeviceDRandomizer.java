package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelsB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceD;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;

class RegisterDeviceDRandomizer implements Randomizer<RegisterDeviceD> {
    private final EnhancedRandom random;

    RegisterDeviceDRandomizer(final EnhancedRandom random) {
        this.random = requireNonNull(random);
    }

    @Override
    public RegisterDeviceD getRandomValue() {
        final RegisterDevice registerDevice = random.nextObject(RegisterDevice.class);
        final List<DeviceChannelB> devices = random.objects(DeviceChannelB.class, random.nextInt(SUPLA_CHANNELMAXCOUNT))
            .collect(toList());
        return new RegisterDeviceD(
            random.nextObject(String.class),
            random.nextObject(String.class),
            registerDevice.getGuid(),
            registerDevice.getName(),
            registerDevice.getSoftVer(),
            random.nextObject(String.class),
            new DeviceChannelsB(devices)
        );
    }
}

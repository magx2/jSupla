package pl.grzeslowski.jsupla.protocol.api.traits;

import java.util.Arrays;
import java.util.stream.Stream;

public interface RegisterDeviceTrait {
    byte[] getGuid();

    byte[] getName();

    byte[] getSoftVer();

    DeviceChannelTrait[] getChannels();

    default Stream<DeviceChannelTrait> getChannelsStream() {
        return Arrays.stream(getChannels());
    }
}

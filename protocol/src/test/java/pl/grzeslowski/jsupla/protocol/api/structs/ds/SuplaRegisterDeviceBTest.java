package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SuplaRegisterDeviceBTest {

    @Test
    void shouldReturnCorrectToString() {
        // given
        final int locationId = 12;
        final byte[] locationPwd = new byte[(int) SUPLA_LOCATION_PWD_MAXSIZE];
        final byte[] guid = new byte[(int) SUPLA_GUID_SIZE];
        final byte[] name = new byte[(int) SUPLA_DEVICE_NAME_MAXSIZE];
        final byte[] softVer = new byte[(int) SUPLA_SOFTVER_MAXSIZE];
        final short channelCount = 0;
        final SuplaDeviceChannelB[] channels = new SuplaDeviceChannelB[0];

        final SuplaRegisterDeviceB device =
                new SuplaRegisterDeviceB(
                        locationId, locationPwd, guid, name, softVer, channelCount, channels);

        // when
        final String toString = device.toString();

        // then
        assertThat(toString)
                .isEqualTo(
                        "SuplaRegisterDeviceB[locationId="
                                + locationId
                                + ", locationPwd="
                                + Arrays.toString(locationPwd)
                                + ", guid="
                                + Arrays.toString(guid)
                                + ", name="
                                + Arrays.toString(name)
                                + ", softVer="
                                + Arrays.toString(softVer)
                                + ", channelCount="
                                + channelCount
                                + ", channels="
                                + channels
                                + "]");
    }
}

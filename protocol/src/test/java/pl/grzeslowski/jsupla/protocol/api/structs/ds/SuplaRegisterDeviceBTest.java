package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

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
        final short channelCount = 2;
        final SuplaDeviceChannelB[] channels =
                new SuplaDeviceChannelB[] {
                    new SuplaDeviceChannelB((short) 1, 100, 200, 300, new byte[8]),
                    new SuplaDeviceChannelB((short) 2, 101, 201, 301, new byte[8])
                };

        final SuplaRegisterDeviceB device =
                new SuplaRegisterDeviceB(
                        locationId, locationPwd, guid, name, softVer, channelCount, channels);

        // when
        final String toString = device.toString();

        // then
        assertThat(toString)
                .isEqualTo(
                        "SuplaRegisterDeviceB[locationId=12, locationPwd=[0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0], guid=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],"
                            + " name=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0], softVer=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,"
                            + " 0, 0, 0, 0, 0, 0, 0], channelCount=2,"
                            + " channels=[SuplaDeviceChannelB[number=1, type=100, funcList=200,"
                            + " defaultValue=300, value=[0, 0, 0, 0, 0, 0, 0, 0]],"
                            + " SuplaDeviceChannelB[number=2, type=101, funcList=201,"
                            + " defaultValue=301, value=[0, 0, 0, 0, 0, 0, 0, 0]]]]");
    }
}

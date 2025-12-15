package pl.grzeslowski.jsupla.protocol.api.structs.cs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DeviceCalCfgRequestATest {

    @Test
    void shouldReturnCorrectToString() {
        // given
        final int channelId = 1;
        final int command = 2023;
        final int dataType = 10;
        final long dataSize = 4;
        final byte[] data = new byte[] {1, 2, 3, 4};

        final DeviceCalCfgRequestA request =
                new DeviceCalCfgRequestA(channelId, command, dataType, dataSize, data);

        // when
        final String toString = request.toString();

        // then
        assertThat(toString)
                .isEqualTo(
                        "DeviceCalCfgRequest[channelId=1, command=2023, dataType=10, dataSize=4,"
                                + " data=[1, 2, 3, 4]]");
    }

    @Test
    void shouldReturnCorrectToStringForEmptyData() {
        // given
        final int channelId = 1;
        final int command = 2023;
        final int dataType = 10;
        final long dataSize = 0;
        final byte[] data = new byte[0];

        final DeviceCalCfgRequestA request =
                new DeviceCalCfgRequestA(channelId, command, dataType, dataSize, data);

        // when
        final String toString = request.toString();

        // then
        assertThat(toString)
                .isEqualTo(
                        "DeviceCalCfgRequest[channelId=1, command=2023, dataType=10, dataSize=0,"
                                + " data=[]]");
    }
}

package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_CMD_START_FIRMWARE_UPDATE;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;

class DeviceCalCfgRequestCodecTest {
    private final DeviceCalCfgRequestCodec codec = DeviceCalCfgRequestCodec.INSTANCE;

    @Test
    void deviceCalCfgRequestShouldRoundTrip() {
        TsdDeviceCalCfgRequest request =
                new TsdDeviceCalCfgRequest(
                        0x01020304,
                        TsdDeviceCalCfgRequest.DEVICE_CHANNEL_NUMBER,
                        SUPLA_CALCFG_CMD_START_FIRMWARE_UPDATE,
                        (byte) 1,
                        0x11121314,
                        3,
                        new byte[] {0x21, 0x22, 0x23});

        TsdDeviceCalCfgRequest decoded = codec.decode(codec.encode(request));

        assertThat(decoded.senderId()).isEqualTo(request.senderId());
        assertThat(decoded.channelNumber()).isEqualTo(request.channelNumber());
        assertThat(decoded.command()).isEqualTo(request.command());
        assertThat(decoded.superUserAuthorized()).isEqualTo(request.superUserAuthorized());
        assertThat(decoded.dataType()).isEqualTo(request.dataType());
        assertThat(decoded.dataSize()).isEqualTo(request.dataSize());
        assertThat(decoded.data()).containsExactly(request.data());
    }

    @Test
    void deviceCalCfgRequestShouldUseLittleEndianFields() {
        TsdDeviceCalCfgRequest request =
                new TsdDeviceCalCfgRequest(
                        0x01020304,
                        -1,
                        SUPLA_CALCFG_CMD_START_FIRMWARE_UPDATE,
                        (byte) 1,
                        0x11121314,
                        1,
                        new byte[] {0x31});

        byte[] encoded = codec.encode(request);

        assertThat(Arrays.copyOfRange(encoded, 0, 4))
                .containsExactly((byte) 0x04, (byte) 0x03, (byte) 0x02, (byte) 0x01);
        assertThat(Arrays.copyOfRange(encoded, 4, 8))
                .containsExactly((byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF);
        assertThat(Arrays.copyOfRange(encoded, 12, 13)).containsExactly((byte) 1);
        assertThat(Arrays.copyOfRange(encoded, 13, 17))
                .containsExactly((byte) 0x14, (byte) 0x13, (byte) 0x12, (byte) 0x11);
        assertThat(Arrays.copyOfRange(encoded, 17, 21))
                .containsExactly((byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00);
    }

    @Test
    void malformedDeviceCalCfgRequestShouldRejectDataSizeLargerThanRemainingPayload() {
        TsdDeviceCalCfgRequest request =
                new TsdDeviceCalCfgRequest(
                        1,
                        -1,
                        SUPLA_CALCFG_CMD_START_FIRMWARE_UPDATE,
                        (byte) 1,
                        0,
                        2,
                        new byte[] {1, 2});
        byte[] encoded = codec.encode(request);
        byte[] truncated = Arrays.copyOf(encoded, encoded.length - 1);

        assertThatThrownBy(() -> codec.decode(truncated))
                .isInstanceOf(ProtocolCodecException.class)
                .hasMessageContaining("exceeds remaining payload bytes");
    }
}

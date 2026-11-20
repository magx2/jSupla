package pl.grzeslowski.jsupla.protocol.api.decoders.dsc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.ChannelStateField.SUPLA_CHANNELSTATE_FIELD_BATTERY_STATE;

import lombok.val;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.dsc.ChannelState;

class ChannelStateDecoderTest {
    ChannelStateDecoder decoder = ChannelStateDecoder.INSTANCE;

    @Test
    void decodeExampleFromAuraton() {
        // given
        byte[] data =
                new byte[] {
                    0, 0, 0, 0, 11, 0, 0, 0, -1, 9, 0, 0, 0, 0, 0, 0, -64, -88, 1, 41, -128, 101,
                    -103, -121, 36, -104, 100, 1, -61, 78, 0, 100, -5, 6, 0, 0, 7, 0, 0, 0, 0, 3, 0,
                    0, 0, 0, 0, 0, 0, 0
                };

        // when
        val decode = decoder.decode(data);

        // then
        assertThat(decode.receiverId()).isZero();
        assertThat(decode.channelId()).isNull();
        assertThat(decode.channelNumber()).isEqualTo((short) 11);
        assertThat(decode.fields()).isEqualTo(2559);
        assertThat(decode.defaultIconField()).isZero();
        assertThat(decode.switchCycleCount()).isNull();
        assertThat(decode.iPv4()).isEqualTo(687974592);
        assertThat(decode.mAC()).isEqualTo(new short[] {128, 101, 153, 135, 36, 152});
        assertThat(decode.batteryLevel()).isEqualTo((short) 100);
        assertThat(decode.batteryPowered()).isEqualTo((short) 1);
        assertThat(decode.wiFiRSSI()).isEqualTo((byte) -61);
        assertThat(decode.wiFiSignalStrength()).isEqualTo((short) 78);
        assertThat(decode.bridgeNodeOnline()).isZero();
        assertThat(decode.bridgeNodeSignalStrength()).isEqualTo((short) 100);
        assertThat(decode.uptime()).isEqualTo(1787);
        assertThat(decode.connectionUptime()).isEqualTo(7);
        assertThat(decode.batteryHealth()).isZero();
        assertThat(decode.lastConnectionResetCause()).isEqualTo((short) 3);
        assertThat(decode.lightSourceLifespan()).isZero();
        assertThat(decode.lightSourceLifespanLeft()).isNull();
        assertThat(decode.lightSourceOperatingTime()).isZero();
        assertThat(decode.operatingTime()).isNull();
        assertThat(decode.eOL()).isOne();
        assertThat(decode.emptySpace()).isEqualTo(new byte[] {0});
    }

    @Test
    void shouldDecodeBatteryStateFromSharedBatteryField() {
        byte[] data = new byte[ChannelState.SIZE];
        int fields = SUPLA_CHANNELSTATE_FIELD_BATTERY_STATE.getValue();
        for (int i = 0; i < Integer.BYTES; i++) {
            data[8 + i] = (byte) (fields >>> (i * Byte.SIZE));
        }
        data[40] = 1;

        val decoded = decoder.decode(data);

        assertThat(decoded.batteryHealth()).isNull();
        assertThat(decoded.batteryState()).isEqualTo((short) 1);
    }
}

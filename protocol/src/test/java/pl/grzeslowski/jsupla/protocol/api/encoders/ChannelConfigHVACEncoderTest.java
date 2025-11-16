package pl.grzeslowski.jsupla.protocol.api.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ChannelConfigHVAC;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACTemperatureCfg;
import pl.grzeslowski.jsupla.protocol.api.structs.HvacParameterFlags;

public class ChannelConfigHVACEncoderTest {
    private static final int AUX_THERMOMETER_CHANNEL_ID = 0x11223344;
    private static final int BINARY_SENSOR_CHANNEL_ID = 0x55667788;
    private static final int HEAT_OR_COLD_SWITCH_CHANNEL_ID = 0x0A0B0C0D;
    private static final int PUMP_SWITCH_CHANNEL_ID = 0x01020305;

    private final ChannelConfigHVACEncoder encoder = ChannelConfigHVACEncoder.INSTANCE;

    @Test
    public void shouldEncodeThermometerChannelIdsInLittleEndianOrder() {
        // given
        ChannelConfigHVAC config = createConfig(0x01020304, null, 0x0F0E0D0C, null, null);

        // when
        byte[] encoded = encoder.encode(config);

        // then
        assertThat(PrimitiveDecoder.INSTANCE.parseInt(encoded, 0)).isEqualTo(0x01020304);
        assertThat(PrimitiveDecoder.INSTANCE.parseInt(encoded, 4))
                .isEqualTo(AUX_THERMOMETER_CHANNEL_ID);
        assertThat(PrimitiveDecoder.INSTANCE.parseInt(encoded, 8))
                .isEqualTo(BINARY_SENSOR_CHANNEL_ID);
    }

    @Test
    public void shouldEncodeThermometerChannelNumberWhenIdMissing() {
        // given
        ChannelConfigHVAC config = createConfig(null, (short) 7, null, (short) 1, (short) 5);

        // when
        byte[] encoded = encoder.encode(config);

        // then
        assertThat(PrimitiveDecoder.INSTANCE.parseUnsignedByte(encoded, 0)).isEqualTo((short) 7);

        int masterUnionOffset = 35;
        assertThat(encoded[masterUnionOffset]).isEqualTo((byte) 1);
        assertThat(encoded[masterUnionOffset + 1]).isEqualTo((byte) 5);
    }

    private ChannelConfigHVAC createConfig(
            Integer mainThermometerChannelId,
            Short mainThermometerChannelNo,
            Integer masterThermostatChannelId,
            Short masterThermostatIsSet,
            Short masterThermostatChannelNo) {
        return new ChannelConfigHVAC(
                mainThermometerChannelId,
                mainThermometerChannelNo,
                AUX_THERMOMETER_CHANNEL_ID,
                null,
                BINARY_SENSOR_CHANNEL_ID,
                null,
                (short) 2,
                (short) 1,
                4,
                5,
                6,
                7,
                (byte) 8,
                (short) 9,
                (short) 10,
                (short) 11,
                (short) 12,
                HvacParameterFlags.builder().build(),
                masterThermostatChannelId,
                masterThermostatIsSet,
                masterThermostatChannelNo,
                HEAT_OR_COLD_SWITCH_CHANNEL_ID,
                PUMP_SWITCH_CHANNEL_ID,
                (short) 13,
                (short) 14,
                (short) 15,
                (short) 16,
                (short) 17,
                new HVACTemperatureCfg(1, new short[24]));
    }
}

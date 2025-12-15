package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.DecimalValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOff;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.StoppableOpenClose;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;

class ChannelTypeEncoderImplTest {
    private final RecordingColorEncoder colorEncoder = new RecordingColorEncoder();
    private final RecordingRelayEncoder relayEncoder = new RecordingRelayEncoder();
    private final RecordingThermometerEncoder thermometerEncoder =
            new RecordingThermometerEncoder();
    private final RecordingStoppableEncoder stoppableEncoder = new RecordingStoppableEncoder();
    private final RecordingElectricityEncoder electricityMeterEncoder =
            new RecordingElectricityEncoder();
    private final RecordingHvacEncoder hvacEncoder = new RecordingHvacEncoder();

    private final ChannelTypeEncoderImpl encoder =
            new ChannelTypeEncoderImpl(
                    colorEncoder,
                    relayEncoder,
                    thermometerEncoder,
                    stoppableEncoder,
                    electricityMeterEncoder,
                    hvacEncoder);

    @Test
    void shouldEncodeOnOffUsingRelayEncoder() {
        byte[] result = encoder.encode(OnOff.ON);

        assertThat(result).isEqualTo(RecordingRelayEncoder.RESPONSE);
        assertThat(relayEncoder.captured).isEqualTo(OnOff.ON);
    }

    @Test
    void shouldEncodeRgbUsingColorEncoder() {
        RgbValue value = new RgbValue((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);

        assertThat(encoder.encode(value)).isEqualTo(RecordingColorEncoder.RESPONSE);
        assertThat(colorEncoder.captured).isEqualTo(value);
    }

    @Test
    void shouldEncodeTemperatureUsingThermometerEncoder() {
        TemperatureValue value = new TemperatureValue(BigDecimal.ONE);

        assertThat(encoder.encode(value)).isEqualTo(RecordingThermometerEncoder.RESPONSE);
        assertThat(thermometerEncoder.temperature).isEqualTo(value);
    }

    @Test
    void shouldEncodeStoppableOpenClose() {
        assertThat(encoder.encode(StoppableOpenClose.CLOSE))
                .isEqualTo(RecordingStoppableEncoder.RESPONSE);
        assertThat(stoppableEncoder.captured).isEqualTo(StoppableOpenClose.CLOSE);
    }

    @Test
    void shouldEncodeElectricityMeterValues() {
        ElectricityMeterValue value =
                new ElectricityMeterValue(
                        BigInteger.valueOf(1),
                        BigInteger.valueOf(2),
                        BigDecimal.valueOf(3),
                        BigDecimal.valueOf(4),
                        Currency.getInstance(Locale.CANADA),
                        5,
                        6,
                        List.of());

        assertThat(encoder.encode(value)).isEqualTo(RecordingElectricityEncoder.RESPONSE);
        assertThat(electricityMeterEncoder.captured).isEqualTo(value);
    }

    @Test
    void shouldEncodeHvacValue() {
        HvacValue value =
                new HvacValue(
                        true,
                        HvacValue.Mode.HEAT,
                        20.0,
                        25.0,
                        new HvacValue.Flags(
                                true, false, false, false, false, false, false, false, false, false,
                                false, false, false));

        assertThat(encoder.encode(value)).isEqualTo(RecordingHvacEncoder.RESPONSE);
        assertThat(hvacEncoder.captured).isEqualTo(value);
    }

    @Test
    void shouldFailWhenDecimalValueIsProvided() {
        assertThatThrownBy(() -> encoder.encode(new DecimalValue(BigDecimal.ONE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    private static class RecordingColorEncoder extends ColorTypeChannelEncoderImpl {
        static final byte[] RESPONSE = new byte[] {7};
        RgbValue captured;

        @Override
        public byte[] encode(RgbValue rgbValue) {
            captured = rgbValue;
            return RESPONSE;
        }
    }

    private static class RecordingRelayEncoder extends RelayTypeChannelEncoderImpl {
        static final byte[] RESPONSE = new byte[] {1, 2};
        OnOff captured;

        @Override
        public byte[] encode(OnOff onOff) {
            captured = onOff;
            return RESPONSE;
        }
    }

    private static class RecordingThermometerEncoder extends ThermometerTypeChannelEncoderImpl {
        static final byte[] RESPONSE = new byte[] {3};
        TemperatureValue temperature;

        @Override
        public byte[] encode(TemperatureValue temperatureValue) {
            this.temperature = temperatureValue;
            return RESPONSE;
        }
    }

    private static class RecordingStoppableEncoder extends StoppableOpenCloseEncoderImpl {
        static final byte[] RESPONSE = new byte[] {5};
        StoppableOpenClose captured;

        @Override
        public byte[] encode(StoppableOpenClose stoppableOpenClose) {
            captured = stoppableOpenClose;
            return RESPONSE;
        }
    }

    private static class RecordingElectricityEncoder extends ElectricityMeterEncoder {
        static final byte[] RESPONSE = new byte[] {8};
        ElectricityMeterValue captured;

        @Override
        public byte[] encode(ElectricityMeterValue proto) {
            captured = proto;
            return RESPONSE;
        }
    }

    private static class RecordingHvacEncoder extends HvacChannelEncoderImpl {
        static final byte[] RESPONSE = new byte[] {9};
        HvacValue captured;

        @Override
        public byte[] encode(HvacValue value) {
            captured = value;
            return RESPONSE;
        }
    }
}

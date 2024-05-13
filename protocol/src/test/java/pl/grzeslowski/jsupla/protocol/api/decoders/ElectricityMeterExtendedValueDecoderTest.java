package pl.grzeslowski.jsupla.protocol.api.decoders;


import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterExtendedValue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ElectricityMeterExtendedValueDecoderTest {
    ElectricityMeterExtendedValueDecoder decoder = ElectricityMeterExtendedValueDecoder.INSTANCE;

    @Test
    public void testDecode() {
        // given
        byte[] bytes = new byte[]{-35, -105, -4, 6, 0, 0, 0, 0, -23, -48, 2, 0, 0, 0, 0, 0, -72, 22, 21, 0, 0, 0, 0, 0, -86, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -41, 84, -92, 3, 0, 0, 0, 0, -91, 111, 0, 0, 0, 0, 0, 0, -23, 103, 4, 0, 0, 0, 0, 0, 20, 61, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -50, -118, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 1, 0, 0, 0, 5, 0, 0, 0, -118, 19, -70, 93, -117, 93, -121, 93, 117, 0, 16, 0, 0, 0, -48, -126, 28, 0, 38, 19, 0, 0, -96, 11, 0, 0, -42, 89, 7, 0, 10, -58, -4, -1, -32, 2, 0, 0, 48, 66, 42, 0, -104, -103, 0, 0, -104, 25, 2, 0, -126, 2, -24, 3, -24, 3, -111, -7, -8, -8, -60, -6, -117, 19, -70, 93, -115, 93, -96, 93, 117, 0, 0, 0, 0, 0, -10, -87, 28, 0, 18, 22, 0, 0, 2, 5, 0, 0, 86, -118, 7, 0, -10, 2, 0, 0, -4, 1, 0, 0, 88, 66, 42, 0, -104, 25, 1, 0, 50, 115, 1, 0, -125, 2, -24, 3, -24, 3, -116, -7, -95, -5, 19, 6, -118, 19, -111, 93, -105, 93, -88, 93, 114, 0, 0, 0, 0, 0, -62, 104, 28, 0, -10, 14, 0, 0, 76, 4, 0, 0, 112, 64, 7, 0, 54, 6, 0, 0, 40, 3, 0, 0, -90, -11, 40, 0, -52, 12, 1, 0, -52, 12, 1, 0, 127, 2, -24, 3, -24, 3, -118, -7, 11, -4, -8, -8, -118, 19, -106, 93, 113, 93, -107, 93, 119, 0, 0, 0, 0, 0, -58, 83, 28, 0, 8, 24, 0, 0, 48, 5, 0, 0, -36, 66, 7, 0, 112, 5, 0, 0, -58, 15, 0, 0, -8, -88, 42, 0, 0, -128, 0, 0, -104, 25, 1, 0, -128, 2, -24, 3, -24, 3, -108, -7, 93, -4, -8, -8, -117, 19, -102, 93, -116, 93, -121, 93, 114, 0, 0, 0, 16, 0, -84, 68, 28, 0, -46, 31, 0, 0, -120, 15, 0, 0, 8, 55, 7, 0, -24, 4, 0, 0, 110, -56, -4, -1, -100, 41, 41, 0, 0, -128, 1, 0, 0, 0, 2, 0, 127, 2, -24, 3, -24, 3, -114, -7, -61, -7, -24, 3};

        // when
        ElectricityMeterExtendedValue value = decoder.decode(bytes);

        // then
        assertThat(value).isNotNull();
    }
}
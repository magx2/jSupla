package pl.grzeslowski.jsupla.proto.parsers;

import org.junit.Test;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

public class TSuplaDataPacketParserTest {
    private final TSuplaDataPacketParser parser = new TSuplaDataPacketParser();

    @Test
    public void shouldParseTSuplaDataPacketFromArrayWithSuplaTag() {

        // given
        byte[] bytes = new byte[]{
                83, 85, 80, 76, 65,
                5,
                1, 0, 0, 0,
                65, 0, 0, 0,
                41, 1, 0, 0,
                -77, 1, 0, 0, 120, 120, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,- 33, 22, - 81, 125, - 64, 116, - 19, 2, 101, 103, - 127, 16, -6 - 66, 96, 60, 90, 65, 77, 69, 76, 32, 82, 79, 87, 45, 48, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 46, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 84, 11, 0, 0, 96, 0, 0, 0 - 116, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 83, 85, 80, 76, 65
        };

        // when
        final TSuplaDataPacket packet = parser.parse(bytes);

        // then
        assertThat(packet.version).isEqualTo((byte) 5);
        assertThat(packet.rrId).isEqualTo(MIN_VALUE + 1);
        assertThat(packet.callType).isEqualTo(MIN_VALUE + 65);
        assertThat(packet.dataSize).isEqualTo(MIN_VALUE + 297);
        assertThat(packet.data).isEqualTo(new byte[]{-77, 1, 0, 0, 120, 120, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -33, 22, -81, 125, -64, 116, -19, 2, 101, 103, -127, 16, -6 - 66, 96, 60, 90, 65, 77, 69, 76, 32, 82, 79, 87, 45, 48, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 46, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 84, 11, 0, 0, 96, 0, 0, 0 - 116, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 83, 85, 80, 76, 65});
    }
}

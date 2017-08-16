package pl.grzeslowski.jsupla.protocol.api.decoders;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.impl.decoders.SuplaDataPacketDecoderImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public class SuplaDataPacketDecoderTest {
    private final SuplaDataPacketDecoderImpl parser = new SuplaDataPacketDecoderImpl(INSTANCE);

    @Test
    public void shouldParseTSuplaDataPacketFromArrayWithSuplaTag() {

        // given
        byte[] bytes = new byte[SUPLA_TAG_SIZE + BYTE_SIZE + INT_SIZE * 3 + 297];
        int i = 0;

        // supla tag
        bytes[i++] = (byte) 83;
        bytes[i++] = (byte) 85;
        bytes[i++] = (byte) 80;
        bytes[i++] = (byte) 76;
        bytes[i++] = (byte) 65;

        // version
        bytes[i++] = (byte) 5;

        // rrId
        bytes[i++] = (byte) 1;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;

        // callType
        bytes[i++] = (byte) 65;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;

        // dataSize
        bytes[i++] = (byte) 41;
        bytes[i++] = (byte) 1;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;

        // data
        bytes[i++] = (byte) -71;
        bytes[i++] = (byte) 1;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 120;
        bytes[i++] = (byte) 120;
        bytes[i++] = (byte) 120;

        for (; i < bytes.length; i++) {
            bytes[i] = 0;
        }

        // when
        final SuplaDataPacket packet = parser.decode(bytes, 0);

        // then
        assertThat(packet.version).isEqualTo((byte) 5);
        assertThat(packet.rrId).isEqualTo(1);
        assertThat(packet.callType).isEqualTo(65);
        assertThat(packet.dataSize).isEqualTo(297);
        assertThat(packet.data[0]).isEqualTo((byte) -71);
        assertThat(packet.data[1]).isEqualTo((byte) 1);
        assertThat(packet.data[2]).isEqualTo((byte) 0);
        assertThat(packet.data[3]).isEqualTo((byte) 0);
        assertThat(packet.data[4]).isEqualTo((byte) 120);
        assertThat(packet.data[5]).isEqualTo((byte) 120);
        assertThat(packet.data[6]).isEqualTo((byte) 120);
    }
}
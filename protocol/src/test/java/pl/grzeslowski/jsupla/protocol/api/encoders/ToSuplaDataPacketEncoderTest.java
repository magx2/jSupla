package pl.grzeslowski.jsupla.protocol.api.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoToSend;

public class ToSuplaDataPacketEncoderTest {
    private final TestEncoder encoder = new TestEncoder();

    @Test
    public void shouldWrapEncodedBytesIntoDataPacket() {
        TestProto proto = new TestProto(new byte[] {7, 8, 9});
        short version = 3;
        long rrId = 44L;

        SuplaDataPacket packet = encoder.encode(proto, version, rrId);

        assertThat(packet.version()).isEqualTo(version);
        assertThat(packet.rrId()).isEqualTo(rrId);
        assertThat(packet.callId())
                .isEqualTo(ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT.getValue());
        assertThat(packet.dataSize()).isEqualTo(proto.protoSize());
        assertThat(packet.data()).containsExactly(proto.payload);
    }

    private static final class TestEncoder implements ToSuplaDataPacketEncoder<TestProto> {
        @Override
        public byte[] encode(TestProto proto) {
            return proto.payload.clone();
        }
    }

    private static final class TestProto implements ProtoToSend {
        private final byte[] payload;

        private TestProto(byte[] payload) {
            this.payload = payload;
        }

        @Override
        public CallType callType() {
            return ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;
        }

        @Override
        public int protoSize() {
            return payload.length;
        }
    }
}

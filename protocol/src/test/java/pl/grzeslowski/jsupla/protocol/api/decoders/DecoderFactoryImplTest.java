package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CALCFG_RESULT;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceCalCfgResult;

class DecoderFactoryImplTest {
    @Test
    void shouldDecodeDeviceCalCfgResultByCallType() {
        int receiverId = 123;
        int channelNumber = 456;
        int command = 789;
        int resultCode = 10;
        byte[] data = new byte[] {1, 2, 3, 4};
        byte[] bytes =
                ByteBuffer.allocate(5 * Integer.BYTES + data.length)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .putInt(receiverId)
                        .putInt(channelNumber)
                        .putInt(command)
                        .putInt(resultCode)
                        .putInt(data.length)
                        .put(data)
                        .array();

        Decoder<DeviceCalCfgResult> decoder =
                DecoderFactoryImpl.INSTANCE.getDecoder(SUPLA_DS_CALL_DEVICE_CALCFG_RESULT);

        DeviceCalCfgResult decoded = decoder.decode(bytes);

        assertThat(decoded.callType()).isEqualTo(SUPLA_DS_CALL_DEVICE_CALCFG_RESULT);
        assertThat(decoded.receiverId()).isEqualTo(receiverId);
        assertThat(decoded.channelNumber()).isEqualTo(channelNumber);
        assertThat(decoded.command()).isEqualTo(command);
        assertThat(decoded.result()).isEqualTo(resultCode);
        assertThat(decoded.dataSize()).isEqualTo(data.length);
        assertThat(decoded.data()).containsExactly(data);
    }
}

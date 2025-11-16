package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;

class TimerTestPayloadBuilder {
    private long remainingMilliseconds;
    private byte[] target = new byte[ProtoConsts.SUPLA_CHANNELVALUE_SIZE];

    TimerTestPayloadBuilder withRemainingMilliseconds(long remainingMilliseconds) {
        this.remainingMilliseconds = remainingMilliseconds;
        return this;
    }

    TimerTestPayloadBuilder withTarget(byte[] target) {
        this.target = target.clone();
        return this;
    }

    byte[] build() {
        ByteBuffer buffer =
                ByteBuffer.allocate(
                        Integer.BYTES
                                + target.length
                                + Integer.BYTES // senderId
                                + Integer.BYTES // senderNameSize
                                + ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt((int) remainingMilliseconds);
        buffer.put(target);
        buffer.putInt(0);
        buffer.putInt(0);
        buffer.put(new byte[ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE]);
        return buffer.array();
    }
}

package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.*;

public final class TDS_SuplaChannelNewValueResult implements DeviceServer {
    /**
     * unsigned
     */
    public final byte channelNumber;
    public final int senderId;
    public final byte success;

    public TDS_SuplaChannelNewValueResult(byte channelNumber, int senderId, byte success) {
        this.channelNumber = channelNumber;
        this.senderId = senderId;
        this.success = success;
    }

    @Override
    public DeviceServerCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE + CHAR_SIZE;
    }

    @Override
    public String toString() {
        return "TDS_SuplaChannelNewValueResult{" +
                "channelNumber=" + channelNumber +
                ", senderId=" + senderId +
                ", success=" + success +
                '}';
    }
}

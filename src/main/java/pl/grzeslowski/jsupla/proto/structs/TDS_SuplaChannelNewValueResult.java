package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import static pl.grzeslowski.jsupla.consts.JavaConsts.*;

public  final class TDS_SuplaChannelNewValueResult implements Proto {
    /**
     * unsigned
     */
    public final char channelNumber;
    public final int senderId;
    public final byte success;

    public TDS_SuplaChannelNewValueResult(char channelNumber, int senderId, byte success) {
        this.channelNumber = channelNumber;
        this.senderId = senderId;
        this.success = success;
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

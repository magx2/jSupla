package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelExtendedValue;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_EXTENDEDVALUE_CHANGED;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

/**
 * @since ver 10.
 */
@ToString
@EqualsAndHashCode
public final class SuplaDeviceChannelExtendedValue implements DeviceServer {
    /**
     * unsigned char
     */
    private final short channelNumber;
    private final SuplaChannelExtendedValue value;

    public SuplaDeviceChannelExtendedValue(short channelNumber, SuplaChannelExtendedValue value) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.value = value;
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_DEVICE_CHANNEL_EXTENDEDVALUE_CHANGED;
    }

    @Override
    public int size() {
        return BYTE_SIZE + value.size();
    }
}

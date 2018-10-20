package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.id;
import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_REGISTER_CLIENT_RESULT_B;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

/**
 * @since ver. 9
 */
public final class SuplaRegisterClientResultB implements ServerClient {
    public static final int SIZE = BYTE_SIZE * 3 + INT_SIZE * 5;
    public final int resultCode;
    public final int clientId;
    public final int locationCount;
    public final int channelCount;
    public final int flags;
    /**
     * unsigned.
     */
    public final short activityTimeout;
    /**
     * unsigned.
     */
    public final short version;
    /**
     * unsigned.
     */
    public final short versionMin;

    public SuplaRegisterClientResultB(final int resultCode,
                                      final int clientId,
                                      final int locationCount,
                                      final int channelCount,
                                      final int flags,
                                      final short activityTimeout,
                                      final short version,
                                      final short versionMin) {
        this.resultCode = resultCode;
        this.clientId = id(clientId);
        this.locationCount = positiveOrZero(locationCount);
        this.channelCount = positiveOrZero(channelCount);
        this.flags = flags;
        this.activityTimeout = positiveOrZero(activityTimeout);
        this.version = positiveOrZero(version);
        this.versionMin = positiveOrZero(versionMin);
        sizeMax(versionMin, version);
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_REGISTER_CLIENT_RESULT_B;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SuplaRegisterClientResultB that = (SuplaRegisterClientResultB) o;
        return resultCode == that.resultCode &&
                       clientId == that.clientId &&
                       locationCount == that.locationCount &&
                       channelCount == that.channelCount &&
                       flags == that.flags &&
                       activityTimeout == that.activityTimeout &&
                       version == that.version &&
                       versionMin == that.versionMin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }

    @Override
    public String toString() {
        return "SuplaRegisterClientResultB{" +
                       "resultCode=" + resultCode +
                       ", clientId=" + clientId +
                       ", locationCount=" + locationCount +
                       ", channelCount=" + channelCount +
                       ", flags=" + flags +
                       ", activityTimeout=" + activityTimeout +
                       ", version=" + version +
                       ", versionMin=" + versionMin +
                       '}';
    }
}

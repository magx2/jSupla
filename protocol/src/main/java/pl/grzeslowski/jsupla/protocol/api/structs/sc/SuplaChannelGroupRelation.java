package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Objects;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

/**
 * @since ver. 9
 */
public final class SuplaChannelGroupRelation implements ServerClient {
    public static final int SIZE = BYTE_SIZE + INT_SIZE * 2;
    public final byte eol;
    public final int channelGroupId;
    public final int channelId;

    public SuplaChannelGroupRelation(final byte eol, final int channelGroupId, final int channelId) {
        this.eol = eol;
        this.channelGroupId = channelGroupId;
        this.channelId = channelId;
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SuplaChannelGroupRelation that = (SuplaChannelGroupRelation) o;
        return eol == that.eol &&
            channelGroupId == that.channelGroupId &&
            channelId == that.channelId;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(eol, channelGroupId, channelId);
    }

    @Override
    public String toString() {
        return "SuplaChannelGroupRelation{" +
            "eol=" + eol +
            ", channelGroupId=" + channelGroupId +
            ", channelId=" + channelId +
            '}';
    }
}

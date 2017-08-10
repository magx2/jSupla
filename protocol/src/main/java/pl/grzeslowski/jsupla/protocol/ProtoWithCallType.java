package pl.grzeslowski.jsupla.protocol;

import pl.grzeslowski.jsupla.protocol.calltypes.CallType;

/**
 * This interface is common for all protocol structures that are send through Supla communication protocol.
 */
public interface ProtoWithCallType extends Proto {
    CallType callType();
}

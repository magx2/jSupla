package pl.grzeslowski.jsupla.protocol.api.types;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;

/**
 * This interface is common for all protocol structures that are send through Supla communication protocol.
 */
public interface ProtoWithCallType extends Proto {
    CallType callType();
}

package pl.grzeslowski.jsupla.protocol.api.types;

/**
 * Classes that implements this interface can be sent in
 * {@link pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket}.
 */
public interface ProtoToSend extends ProtoWithCallType, ProtoWithSize {}

package pl.grzeslowski.jsupla.protocol;

/**
 * Classes that implements this interface can be sent in {@link pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket}.
 */
public interface ProtoToSend extends ProtoWithCallType, ProtoWithSize {
}

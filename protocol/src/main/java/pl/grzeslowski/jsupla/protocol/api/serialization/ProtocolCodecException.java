package pl.grzeslowski.jsupla.protocol.api.serialization;

public class ProtocolCodecException extends IllegalArgumentException {
    public ProtocolCodecException(String message) {
        super(message);
    }

    public ProtocolCodecException(String message, Throwable cause) {
        super(message, cause);
    }
}

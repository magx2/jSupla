package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.types.Proto;

import static java.lang.String.format;

@SuppressWarnings("SerializableHasSerializationMethods")
public class UnionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnionException(Proto proto, String... fields) {
        super(format("All fields in union [%s] are null! proto=%s",
            String.join("|", fields),
            proto));
    }
}

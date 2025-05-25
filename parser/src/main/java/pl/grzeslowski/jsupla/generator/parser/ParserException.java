package pl.grzeslowski.jsupla.generator.parser;

import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import java.io.Serial;

@SuppressWarnings("SerializableHasSerializationMethods")
public class ParserException extends IllegalStateException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ParserException(Token.Position position, String msg) {
        super(position.toString() + " " + msg);
    }
}

package pl.grzeslowski.jsupla.generator.parser;

import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

class TokenQueue {
    private final Queue<? extends Token> queue;

    TokenQueue(Queue<? extends Token> queue) {
        this.queue = queue;
    }

    TokenQueue(Collection<? extends Token> tokens) {
        this(new LinkedList<>(tokens));
    }

    Token peek() {
        return queue.peek();
    }

    Token poll() {
        return queue.poll();
    }

    <T extends Token> T peek(Class<T> clazz) {
        var token = queue.peek();
        if (token == null) {
            throw new NullPointerException("Expected token but got null");
        }
        if (!clazz.isInstance(token)) {
            throw new ParserException(token.position(),
                "Expected %s class but got %s (value %s)".formatted(
                    clazz.getSimpleName(),
                    Optional.ofNullable(peek())
                        .map(Token::getClass)
                        .map(Class::getSimpleName)
                        .orElse("null"),
                    token));
        }
        return clazz.cast(token);
    }

    @SuppressWarnings("SameParameterValue")
    <T extends Token> T poll(Class<T> clazz) {
        var token = queue.poll();
        if (token == null) {
            throw new NullPointerException("Expected token but got null");
        }
        if (!clazz.isInstance(token)) {
            throw new ParserException(token.position(), "Expected %s class but got %s (value %s)".formatted(
                clazz.getSimpleName(),
                Optional.ofNullable(peek())
                    .map(Token::getClass)
                    .map(Class::getSimpleName)
                    .orElse("null"),
                token));
        }
        return clazz.cast(token);
    }

    void pollAndCheck(Token expected) {
        var token = queue.poll();
        if (token == null) {
            throw new NullPointerException("Expected token but got null");
        }
        if (token != expected) {
            throw new ParserException(token.position(), "Expected %s but got %s".formatted(expected, token));
        }
    }

    Token pollAndCheck(Token.CodeKeyword expectedKeyword) {
        var token = queue.poll();
        if (token == null) {
            throw new NullPointerException("Expected token but got null");
        }
        if (!(token instanceof Token.Keyword keyword)) {
            throw new ParserException(token.position(), "Expected token to be Token.Keyword but got %s".formatted(token));
        }
        if (keyword.keyword() != expectedKeyword) {
            throw new ParserException(token.position(), "Expected %s but got %s".formatted(expectedKeyword, token));
        }
        return token;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

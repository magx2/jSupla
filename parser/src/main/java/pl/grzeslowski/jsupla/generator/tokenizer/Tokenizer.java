package pl.grzeslowski.jsupla.generator.tokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.CommentToken;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.Position;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.DEFINE;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.ENTER;

public class Tokenizer {
    public static final Tokenizer INSTANCE = new Tokenizer();
    private static final Logger log = LoggerFactory.getLogger(Tokenizer.class);

    public List<Token> tokenize(String code) {
        var tokens = new ArrayList<Token>();
        int i = 0;
        int line = 1;
        int column = 1;

        while (i < code.length()) {
            char c = code.charAt(i);
            final var currentPosition = new Position(line, column);

            if (c == '\n') {
                if (code.charAt(i - 1) != '\\') {
                    if (tokens.size() >= 3) {
                        var token = tokens.get(tokens.size() - 3);// -3 from back
                        if (token.is(DEFINE)) {
                            tokens.add(new Token.Keyword(currentPosition, ENTER));
                        }
                    }
                    if (tokens.size() >= 2) {
                        var token = tokens.get(tokens.size() - 2);// -2 from back
                        if (token.is(DEFINE)) {
                            tokens.add(new Token.Keyword(currentPosition, ENTER));
                        }
                    }
                }
                line++;
                column = 1;
                i++;
                continue;
            }

            // Handle single-line comment
            if (c == '/' && i + 1 < code.length() && code.charAt(i + 1) == '/') {
                int start = i;
                i += 2;
                column += 2;
                while (i < code.length() && code.charAt(i) != '\n') {
                    i++;
                    column++;
                }
                var comment = code.substring(start + 2, i).trim();
                if (tokens.size() >= 2) {
                    var token = tokens.get(tokens.size() - 2);// -2 from back
                    if (token.is(DEFINE)) {
                        tokens.add(new Token.Keyword(currentPosition, ENTER));
                    }
                }
                tokens.add(new CommentToken(currentPosition, comment));
                squashComments(tokens);
                continue;
            }

            // Handle multi-line comment
            if (c == '/' && i + 1 < code.length() && code.charAt(i + 1) == '*') {
                int start = i;
                i += 2;
                if (code.charAt(i) == '\n') {
                    line++;
                    column = 1;
                } else {
                    column += 2;
                }
                while (i + 1 < code.length() && !(code.charAt(i) == '*' && code.charAt(i + 1) == '/')) {
                    i++;
                    if (code.charAt(i) == '\n') {
                        line++;
                        column = 1;
                    } else {
                        column++;
                    }
                }
                i += 2; // consume "*/"
                column += 2;
                if (tokens.size() >= 2) {
                    var token = tokens.get(tokens.size() - 2);// -2 from back
                    if (token.is(DEFINE)) {
                        tokens.add(new Token.Keyword(currentPosition, ENTER));
                    }
                }
                tokens.add(new CommentToken(currentPosition, code.substring(start, i)));
                squashComments(tokens);
                continue;
            }

            // Skip whitespace
            if (Character.isWhitespace(c)) {
                i++;
                column++;
                continue;
            }

            // Handle preprocessor directive (e.g., #define)
            if (c == '#') {
                int start = i;
                i++;
                column++;
                while (i < code.length() && (Character.isLetter(code.charAt(i)) || code.charAt(i) == '_')) {
                    i++;
                    column++;
                }
                var value = code.substring(start + "#".length(), i);
                var token = CodeKeyword.parseKeyword(value)
                    .map(ck -> new Token.Keyword(currentPosition, ck))
                    .map(t -> (Token) t)
                    .orElse(new SimpleToken(currentPosition, value));
                tokens.add(token);
                continue;
            }

            // Handle triple-character operators
            if (i + 2 < code.length()) {
                String threeCharOp = code.substring(i, i + 3);
                if (threeCharOp.equals("<<=") || threeCharOp.equals(">>=")) {
                    var token = CodeKeyword.parseKeyword(threeCharOp)
                        .map(ck -> new Token.Keyword(currentPosition, ck))
                        .map(t -> (Token) t)
                        .orElse(new SimpleToken(currentPosition, threeCharOp));
                    tokens.add(token);
                    i += 3;
                    column += 3;
                    continue;
                }
            }

            // Handle double-character operators
            if (i + 1 < code.length()) {
                String twoCharOp = code.substring(i, i + 2);
                if (twoCharOp.equals("<<") || twoCharOp.equals(">>") ||
                    twoCharOp.equals("+=") || twoCharOp.equals("-=") || twoCharOp.equals("*=") ||
                    twoCharOp.equals("/=") || twoCharOp.equals("&&") || twoCharOp.equals("||") ||
                    twoCharOp.equals("==") || twoCharOp.equals("!=") || twoCharOp.equals("<=") || twoCharOp.equals(">=")) {
                    var token = CodeKeyword.parseKeyword(twoCharOp)
                        .map(ck -> new Token.Keyword(currentPosition, ck))
                        .map(t -> (Token) t)
                        .orElse(new SimpleToken(currentPosition, twoCharOp));
                    tokens.add(token);
                    i += 2;
                    column += 2;
                    continue;
                }
            }

            // Symbols including brackets and common punctuation
            if ("{}();:=+-*/[]<>!&|".indexOf(c) >= 0) {
                var token = CodeKeyword.parseKeyword(Character.toString(c))
                    .map(ck -> new Token.Keyword(currentPosition, ck))
                    .map(t -> (Token) t)
                    .orElse(new SimpleToken(currentPosition, c));
                tokens.add(token);
                i++;
                column++;
                continue;
            }

            // Hexadecimal numbers
            if (c == '0' && i + 1 < code.length() && (code.charAt(i + 1) == 'x' || code.charAt(i + 1) == 'X')) {
                int start = i;
                i += 2;
                column += 2;
                while (i < code.length() && Character.digit(code.charAt(i), 16) != -1) {
                    i++;
                    column++;
                }
                tokens.add(new SimpleToken(currentPosition, code.substring(start, i)));
                continue;
            }

            // Decimal numbers
            if (Character.isDigit(c)) {
                int start = i;
                while (i < code.length() && Character.isDigit(code.charAt(i))) {
                    i++;
                    column++;
                }
                // Check for ULL, UL, LL, L, U suffixes
                int suffixStart = i;
                while (i < code.length() && Character.isLetter(code.charAt(i))) {
                    i++;
                    column++;
                }
                tokens.add(new SimpleToken(currentPosition, code.substring(start, i)));
                continue;
            }

            // Identifiers / Keywords
            if (Character.isLetter(c) || c == '_') {
                int start = i;
                while (i < code.length() &&
                    (Character.isLetterOrDigit(code.charAt(i)) || code.charAt(i) == '_')) {
                    i++;
                    column++;
                }
                var value = code.substring(start, i);
                var token = CodeKeyword.parseKeyword(value)
                    .map(ck -> new Token.Keyword(currentPosition, ck))
                    .map(t -> (Token) t)
                    .orElse(new SimpleToken(currentPosition, value));
                tokens.add(token);
                continue;
            }

            // Unknown character
            log.debug("{}: Unknown character: `{}`", currentPosition, c);
            i++;
            column++;
        }

        return unmodifiableList(tokens);
    }

    private void squashComments(List<Token> tokens) {
        if (tokens.size() < 2) {
            return;
        }
        var secondToLast = tokens.get(tokens.size() - 2);
        var last = tokens.get(tokens.size() - 1);
        if (secondToLast instanceof CommentToken secondComment
            && last instanceof CommentToken lastComment) {
            tokens.remove(tokens.size() - 2);
            tokens.remove(tokens.size() - 1);
            var squashComment = CommentToken.squash(secondComment, lastComment);
            tokens.add(squashComment);
        }
    }
}

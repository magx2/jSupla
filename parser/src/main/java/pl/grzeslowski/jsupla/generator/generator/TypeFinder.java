package pl.grzeslowski.jsupla.generator.generator;

import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;

import javax.annotation.Nullable;
import java.util.List;

import static pl.grzeslowski.jsupla.generator.generator.JavaType.PrimitiveType.*;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.ENTER;

public class TypeFinder {
    public static final TypeFinder INSTANCE = new TypeFinder();
    private static final String SUPLA_BYTE = "byte";
    private static final String SUPLA_CHAR = "char";
    private static final String SUPLA_SHORT = "_supla_int16_t";
    private static final String SUPLA_INT = "_supla_int_t";
    private static final String SUPLA_LONG = "_supla_int64_t";
    private static final String UNSIGNED = "unsigned";

    private TypeFinder() {
    }

    public JavaType findType(List<? extends Token> tokens) {
        return findType(tokens, null);
    }

    public JavaType findType(List<? extends Token> tokens, @Nullable String byteSizeString) {
        if (tokens.isEmpty()) {
            return BOOLEAN;
        }
        if (tokens.size() == 1 && tokens.get(0).is(ENTER)) {
            return BOOLEAN;
        }
        Integer byteSize = null;
        if (byteSizeString != null) {
            try {
                byteSize = Integer.parseInt(byteSizeString);
            } catch (NumberFormatException e) {
                // do not log
            }
        }
        if (byteSize != null && byteSize == 1) {
            return BOOLEAN;
        }
        JavaType javaType = null;
        boolean unsigned = false;
        for (var token : tokens) {
            if (token instanceof SimpleToken simpleToken) {
                if (UNSIGNED.equals(simpleToken.value())) {
                    unsigned = true;
                    continue;
                }
                JavaType actualType;
                var value = simpleToken.value();
                if (value.endsWith("L") || value.startsWith("0x")) {
                    actualType = unsigned ? ULONG : LONG;
                } else if (value.matches("-?\\d+")
                    || value.matches("[A-Z]+(_[A-Z]+)*")) {
                    actualType = unsigned ? UINT : INT;
                } else if (SUPLA_BYTE.equals(value) || SUPLA_CHAR.equals(value)) {
                    actualType = unsigned ? UBYTE : BYTE;
                } else if (SUPLA_SHORT.equals(value)) {
                    actualType = unsigned ? USHORT : SHORT;
                } else if (SUPLA_INT.equals(value)) {
                    actualType = unsigned ? UINT : INT;
                } else if (SUPLA_LONG.equals(value)) {
                    actualType = unsigned ? ULONG : LONG;
                } else {
                    actualType = new ReferenceType(value);
                }
                javaType = join(javaType, actualType);
            }
        }
        return javaType;
    }

    private JavaType join(@Nullable JavaType javaType, JavaType actualType) {
        if (javaType == null) {
            return actualType;
        }
        if (javaType == LONG || actualType == LONG) {
            return LONG;
        }
        return javaType;
    }

}

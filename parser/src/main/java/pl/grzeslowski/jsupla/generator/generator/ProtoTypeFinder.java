package pl.grzeslowski.jsupla.generator.generator;

import pl.grzeslowski.jsupla.generator.parser.Struct;

import javax.annotation.Nullable;

import static java.util.Arrays.stream;
import static pl.grzeslowski.jsupla.generator.generator.ProtoTypeFinder.ProtoType.T;

class ProtoTypeFinder {
    static final ProtoTypeFinder INSTANCE = new ProtoTypeFinder();

    private ProtoTypeFinder() {
    }

    ProtoType findType(Struct struct) {
        if (struct.orderComment() != null) {
            var protoType = stream(ProtoType.values())
                    .filter(type -> type.orderComment != null)
                    .filter(type -> struct.orderComment().matches(type.orderComment))
                    .findFirst();
            if (protoType.isPresent()) {
                return protoType.get();
            }
        }
        return stream(ProtoType.values())
                .filter(type -> struct.name().startsWith(type.type))
                .findFirst()
                .orElse(T);
    }

    enum ProtoType {
        TSCD("TSCD", null),
        TSDC("TSDC", "server -> device[/|]client"),
        TSCS("TSCS", null),
        TCSD("TCSD", "Client -> Server -> Device"),
        TDCS("TDCS", "device[/|]client -> server"),
        TDSC("TDSC", null),
        TSDS("TSDS", null),
        TCS("TCS", "client -> server"),
        TSC("TSC", "server -> client"),
        TSD("TSD", "server -> device"),
        TDS("TDS", "device -> server"),
        T("T", "");

        private final String type;
        @Nullable
        private final String orderComment;

        ProtoType(String type, @Nullable String orderComment) {
            this.type = type;
            this.orderComment = orderComment;
        }

        public String getType() {
            return type;
        }

    }
}

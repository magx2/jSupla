package pl.grzeslowski.jsupla.protocoljava.api.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.NewValue;

public interface NewValueSerializer extends ClientServerSerializer<NewValue, SuplaNewValue> {
}

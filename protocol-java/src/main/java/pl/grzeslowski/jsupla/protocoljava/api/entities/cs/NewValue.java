package pl.grzeslowski.jsupla.protocoljava.api.entities.cs;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.byteSize;
import static pl.grzeslowski.jsupla.Preconditions.id;

public class NewValue implements ClientServerEntity {
    @Positive
    private final int id;
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int target;
    @NotNull
    @Valid
    private final ChannelValue value;

    public NewValue(
        @Positive int id,
        @Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) int target,
        @NotNull @Valid ChannelValue value) {
        this.id = id(id);
        this.target = byteSize(target);
        this.value = requireNonNull(value);
    }

    public int getId() {
        return id;
    }

    public int getTarget() {
        return target;
    }

    public ChannelValue getValue() {
        return value;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewValue)) {
            return false;
        }
        NewValue newValue = (NewValue) o;
        return id == newValue.id &&
            target == newValue.target &&
            Objects.equals(value, newValue.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, target, value);
    }

    @Override
    public String toString() {
        return "NewValue{" +
            "id=" + id +
            ", target=" + target +
            ", value=" + value +
            '}';
    }
}

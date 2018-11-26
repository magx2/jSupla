package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaNewValueDecoderImplTest extends ProperDecoderTest<SuplaNewValue> {
    @InjectMocks SuplaNewValueDecoderImpl decoder;

    int id;
    byte target;
    byte[] value;

    @Before
    public void setUp() {
        id = RANDOM_SUPLA.nextInt();
        target = RANDOM_SUPLA.nextByte();
        value = RANDOM_SUPLA.nextByteArray(SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public ProtoWithSizeDecoder<SuplaNewValue> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaNewValue.SIZE;
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    protected byte[] givenParseEntity(int offset) {
        final byte[] bytes = new byte[entitySize() + offset];

        offset += INSTANCE.writeInteger(id, bytes, offset);
        offset += INSTANCE.writeByte(target, bytes, offset);
        offset += INSTANCE.writeBytes(value, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaNewValue entity) {
        assertThat(entity.id).isEqualTo(id);
        assertThat(entity.target).isEqualTo(target);
        assertThat(entity.value).isEqualTo(value);
    }
}

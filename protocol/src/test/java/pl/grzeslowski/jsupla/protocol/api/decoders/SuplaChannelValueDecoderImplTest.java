package pl.grzeslowski.jsupla.protocol.api.decoders;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelValueDecoderImplTest extends ProperDecoderTest<SuplaChannelValue> {
    @InjectMocks
    SuplaChannelValueDecoderImpl decoder;
    private byte[] value;
    private byte[] subValue;

    @Override
    public SuplaChannelValueDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaChannelValue.SIZE;
    }

    @Override
    protected byte[] givenParseEntity(int offset) {
        final byte[] bytes = new byte[entitySize() + offset];

        value = RANDOM_SUPLA.nextBytesWithSize(SUPLA_CHANNELVALUE_SIZE);
        offset += PrimitiveEncoderImpl.INSTANCE.writeBytes(value, bytes, offset);

        subValue = RANDOM_SUPLA.nextBytesWithSize(SUPLA_CHANNELVALUE_SIZE);
        PrimitiveEncoderImpl.INSTANCE.writeBytes(subValue, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaChannelValue entity) {
        assertThat(entity.value).isEqualTo(value);
        assertThat(entity.subValue).isEqualTo(subValue);
    }

}
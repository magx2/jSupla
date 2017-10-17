package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelValueDecoderImplTest extends ProperDecoderTest<SuplaChannelValue> {
    @InjectMocks SuplaChannelValueDecoderImpl decoder;
    @Mock SuplaChannelValueDecoder channelValueDecoder;
    private pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue value;
    private byte eol;
    private int id;
    private byte online;

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
        final byte[] bytes = new byte[SuplaChannelValue.SIZE + offset];

        eol = RANDOM_SUPLA.nextByte();
        offset += PrimitiveEncoderImpl.INSTANCE.writeByte(eol, bytes, offset);

        id = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(id, bytes, offset);

        online = RANDOM_SUPLA.nextBoolByte();
        offset += PrimitiveEncoderImpl.INSTANCE.writeByte(online, bytes, offset);

        value = RANDOM_SUPLA.nextObject(pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class);
        given(channelValueDecoder.decode(any(), eq(offset)))
                .willReturn(value);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaChannelValue entity) {
        assertThat(entity.eol).isEqualTo(eol);
        assertThat(entity.id).isEqualTo(id);
        assertThat(entity.online).isEqualTo(online);
        assertThat(entity.value).isEqualTo(value);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenChannelValueDecoderIsNull() throws Exception {
        new SuplaChannelValueDecoderImpl(null);
    }
}
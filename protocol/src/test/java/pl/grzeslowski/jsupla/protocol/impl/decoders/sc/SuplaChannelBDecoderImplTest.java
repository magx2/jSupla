package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelBDecoderImplTest extends ProperDecoderTest<SuplaChannelB> {
    @InjectMocks
    SuplaChannelBDecoderImpl decoder;
    @Mock
    SuplaChannelValueDecoder suplaChannelValueDecoder;

    byte eol;
    int id;
    int locationId;
    int func;
    int altIcon;
    long flags;
    short protocolVersion;
    byte online;
    SuplaChannelValue value;
    long captionSize;
    byte[] caption;

    @Before
    public void setUp() {
        eol = RANDOM_SUPLA.nextByte();
        id = RANDOM_SUPLA.nextInt();
        locationId = RANDOM_SUPLA.nextInt();
        func = RANDOM_SUPLA.nextInt();
        altIcon = RANDOM_SUPLA.nextInt();
        flags = RANDOM_SUPLA.nextUnsignedInt();
        protocolVersion = RANDOM_SUPLA.nextUnsignedByte();
        online = RANDOM_SUPLA.nextByte();
        value = RANDOM_SUPLA.nextObject(SuplaChannelValue.class);
        captionSize = RANDOM_SUPLA.nextUnsignedInt(100);
        caption = new byte[(int) captionSize];
        for (int i = 0; i < caption.length; i++) {
            caption[i] = RANDOM_SUPLA.nextByte();
        }
    }

    @Before
    public void setUpMocks() {
        given(suplaChannelValueDecoder.decode(any(), anyInt())).willReturn(value);
    }

    @Override
    public ProtoWithSizeDecoder<SuplaChannelB> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaChannelB.MIN_SIZE + caption.length * BYTE_SIZE;
    }

    @Override
    protected byte[] givenParseEntity(int offset) {
        final byte[] bytes = new byte[entitySize() + offset];

        offset += PrimitiveEncoderImpl.INSTANCE.writeByte(eol, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(id, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(locationId, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(func, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(altIcon, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedInteger(flags, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(protocolVersion, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeByte(online, bytes, offset);
        given(suplaChannelValueDecoder.decode(bytes, offset)).willReturn(value);
        offset += PrimitiveEncoderImpl.INSTANCE.writeBytes(RANDOM_SUPLA.nextByteArray(value.size()), bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedInteger(captionSize, bytes, offset);
        PrimitiveEncoderImpl.INSTANCE.writeBytes(caption, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(SuplaChannelB entity) {
        assertThat(entity.eol).isEqualTo(eol);
        assertThat(entity.id).isEqualTo(id);
        assertThat(entity.locationId).isEqualTo(locationId);
        assertThat(entity.func).isEqualTo(func);
        assertThat(entity.altIcon).isEqualTo(altIcon);
        assertThat(entity.flags).isEqualTo(flags);
        assertThat(entity.protocolVersion).isEqualTo(protocolVersion);
        assertThat(entity.online).isEqualTo(online);
        assertThat(entity.value).isEqualTo(value);
        assertThat(entity.captionSize).isEqualTo(captionSize);
        assertThat(entity.caption).isEqualTo(caption);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenParameterIsNull() {
        new SuplaChannelBDecoderImpl(null);
    }
}

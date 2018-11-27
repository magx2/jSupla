package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings({"WeakerAccess", "UnusedAssignment"})
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelGroupDecoderImplTest extends ProperDecoderTest<SuplaChannelGroup> {
    @InjectMocks SuplaChannelGroupDecoderImpl decoder;

    byte eol;
    int id;
    int locationId;
    int func;
    int altIcon;
    long flags;
    long captionSize;
    byte[] caption;

    SuplaChannelGroup entity;

    @Before
    public void setUp() {
        eol = RANDOM_SUPLA.nextByte();
        id = RANDOM_SUPLA.nextPositiveInt();
        locationId = RANDOM_SUPLA.nextPositiveInt();
        func = RANDOM_SUPLA.nextInt();
        altIcon = RANDOM_SUPLA.nextInt();
        flags = RANDOM_SUPLA.nextUnsignedInt();
        captionSize = RANDOM_SUPLA.nextPositiveInt(100);
        caption = RANDOM_SUPLA.nextByteArray((int) captionSize);

        entity = new SuplaChannelGroup(
                eol,
                id,
                locationId,
                func,
                altIcon,
                flags,
                captionSize,
                caption
        );
    }

    @Override
    public ProtoWithSizeDecoder<SuplaChannelGroup> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return entity.size();
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
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedInteger(captionSize, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeBytes(caption, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaChannelGroup entity) {
        assertThat(entity).isEqualTo(this.entity);
    }
}
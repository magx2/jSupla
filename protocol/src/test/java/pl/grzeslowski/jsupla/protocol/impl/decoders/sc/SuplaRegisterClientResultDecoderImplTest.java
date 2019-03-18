package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientResultDecoderImplTest extends ProperDecoderTest<SuplaRegisterClientResult> {
    @InjectMocks SuplaRegisterClientResultDecoderImpl decoder;
    final SuplaRegisterClientResult proto = RANDOM_SUPLA.nextObject(SuplaRegisterClientResult.class);

    @Override
    public SuplaRegisterClientResultDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaRegisterClientResult.SIZE;
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    protected byte[] givenParseEntity(int offset) {
        final byte[] bytes = new byte[SuplaRegisterClientResult.SIZE + offset];

        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(proto.resultCode, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(proto.clientId, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(proto.locationCount, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(proto.channelCount, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(proto.activityTimeout, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(proto.version, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(proto.versionMin, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaRegisterClientResult entity) {
        assertThat(entity.resultCode).isEqualTo(proto.resultCode);
        assertThat(entity.clientId).isEqualTo(proto.clientId);
        assertThat(entity.locationCount).isEqualTo(proto.locationCount);
        assertThat(entity.channelCount).isEqualTo(proto.channelCount);
        assertThat(entity.activityTimeout).isEqualTo(proto.activityTimeout);
        assertThat(entity.version).isEqualTo(proto.version);
        assertThat(entity.versionMin).isEqualTo(proto.versionMin);
    }
}
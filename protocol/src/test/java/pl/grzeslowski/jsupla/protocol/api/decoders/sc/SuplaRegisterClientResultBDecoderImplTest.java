package pl.grzeslowski.jsupla.protocol.api.decoders.sc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientResultBDecoderImplTest extends ProperDecoderTest<SuplaRegisterClientResultB> {
    @InjectMocks
    SuplaRegisterClientResultBDecoderImpl decoder;

    int resultCode;
    int clientId;
    int locationCount;
    int channelCount;
    int flags;
    short activityTimeout;
    short version;
    short versionMin;

    @Override
    public ProtoWithSizeDecoder<SuplaRegisterClientResultB> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaRegisterClientResultB.SIZE;
    }

    @Override
    protected byte[] givenParseEntity(int offset) {
        final byte[] bytes = new byte[entitySize() + offset];

        resultCode = RANDOM_SUPLA.nextInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(resultCode, bytes, offset);
        ;

        clientId = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(clientId, bytes, offset);

        locationCount = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(locationCount, bytes, offset);

        channelCount = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(channelCount, bytes, offset);

        flags = RANDOM_SUPLA.nextInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(flags, bytes, offset);

        activityTimeout = RANDOM_SUPLA.nextUnsignedByte();
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(activityTimeout, bytes, offset);

        version = RANDOM_SUPLA.nextUnsignedByte();
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(version, bytes, offset);

        versionMin = RANDOM_SUPLA.nextUnsignedByte(version);
        PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(versionMin, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(SuplaRegisterClientResultB entity) {
        assertThat(entity.resultCode).isEqualTo(resultCode);
        assertThat(entity.clientId).isEqualTo(clientId);
        assertThat(entity.locationCount).isEqualTo(locationCount);
        assertThat(entity.channelCount).isEqualTo(channelCount);
        assertThat(entity.flags).isEqualTo(flags);
        assertThat(entity.activityTimeout).isEqualTo(activityTimeout);
        assertThat(entity.version).isEqualTo(version);
        assertThat(entity.versionMin).isEqualTo(versionMin);
    }
}

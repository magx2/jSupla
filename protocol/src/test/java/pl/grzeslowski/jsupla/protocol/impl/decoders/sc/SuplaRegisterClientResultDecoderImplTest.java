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
    private int resultCode;
    private int clientId;
    private int locationCount;
    private int chanelCount;
    private short activityTimeout;
    private short version;
    private short versionMin;

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

        resultCode = RANDOM_SUPLA.nextInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(resultCode, bytes, offset);

        clientId = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(clientId, bytes, offset);

        locationCount = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(locationCount, bytes, offset);

        chanelCount = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(chanelCount, bytes, offset);

        activityTimeout = RANDOM_SUPLA.nextUnsignedByte();
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(activityTimeout, bytes, offset);

        version = (short) (RANDOM_SUPLA.nextUnsignedByte() + 2);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(version, bytes, offset);

        versionMin = RANDOM_SUPLA.nextUnsignedByte(version);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(versionMin, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaRegisterClientResult entity) {
        assertThat(entity.resultCode).isEqualTo(resultCode);
        assertThat(entity.clientId).isEqualTo(clientId);
        assertThat(entity.locationCount).isEqualTo(locationCount);
        assertThat(entity.channelCount).isEqualTo(chanelCount);
        assertThat(entity.activityTimeout).isEqualTo(activityTimeout);
        assertThat(entity.version).isEqualTo(version);
        assertThat(entity.versionMin).isEqualTo(versionMin);
    }
}
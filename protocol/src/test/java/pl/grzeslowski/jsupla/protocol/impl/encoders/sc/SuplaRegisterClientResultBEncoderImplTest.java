package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientResultBEncoderImplTest extends EncoderTest<SuplaRegisterClientResultB> {
    @InjectMocks
    SuplaRegisterClientResultBEncoderImpl encoder;

    SuplaRegisterClientResultB proto;

    @Before
    public void setUp() {
        final short version = RANDOM_SUPLA.nextUnsignedByte();
        proto = new SuplaRegisterClientResultB(
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextPositiveInt(),
            RANDOM_SUPLA.nextPositiveInt(),
            RANDOM_SUPLA.nextPositiveInt(),
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextUnsignedByte(),
            version,
            RANDOM_SUPLA.nextUnsignedByte(version)
        );
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterClientResultB proto) {
        int offset = 0;
        verify(primitiveEncoder).writeInteger(proto.resultCode, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.clientId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.locationCount, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.channelCount, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.flags, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.activityTimeout, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.version, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.versionMin, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientResultBEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaRegisterClientResultB> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterClientResultB getProto() {
        return proto;
    }
}
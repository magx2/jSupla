package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB.SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientBDecoderImplTest extends DecoderTest {
    @InjectMocks SuplaRegisterClientBDecoderImpl decoder;

    @Override
    public void shouldParseEntity() throws Exception {

        // given
        int offset = 5;
        byte[] bytes = new byte[SIZE + offset];

        // when
        decoder.decode(bytes, offset);

        // then
        verify(primitiveDecoder).parseInt(bytes, offset);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE,
                offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE,
                offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE
                                                            + SUPLA_GUID_SIZE,
                offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE +
                                                            SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE,
                offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE
                        + SUPLA_SOFTVER_MAXSIZE);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE
                                                            + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE
                                                            + SUPLA_SOFTVER_MAXSIZE,
                offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE
                        + SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientBDecoderImpl(null);
    }

    @Override
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmall() throws Exception {

        // given
        final byte[] bytes = new byte[SuplaChannelNewValueB.SIZE - 1];

        // when
        decoder.decode(bytes, 0);
    }

    @Override
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmallAfterAddingOffset() throws Exception {

        // given
        final byte[] bytes = new byte[SuplaChannelNewValueB.SIZE];

        // when
        decoder.decode(bytes, 1);
    }
}
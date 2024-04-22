package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_PACK_MAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelValuePackEncoderImplTest extends EncoderTest<SuplaChannelValuePack> {
    @InjectMocks
    SuplaChannelValuePackEncoderImpl encoder;
    @Mock
    SuplaChannelValueEncoder suplaChannelValueEncoder;

    SuplaChannelValuePack proto;

    @Before
    public void setUp() {
        final int count = RANDOM_SUPLA.nextPositiveInt(SUPLA_CHANNELVALUE_PACK_MAXCOUNT);
        proto = new SuplaChannelValuePack(
            count,
            RANDOM_SUPLA.nextPositiveInt(),
            Stream.generate(() -> RANDOM_SUPLA.nextObject(SuplaChannelValue.class))
                .limit(count)
                .toArray(SuplaChannelValue[]::new));
        for (SuplaChannelValue item : proto.items) {
            given(suplaChannelValueEncoder.encode(item)).willReturn(new byte[item.size()]);
        }
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelValuePack proto) {
        int offset = 0;
        verify(primitiveEncoder).writeInteger(proto.count, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.totalLeft, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        for (SuplaChannelValue item : proto.items) {
            verify(suplaChannelValueEncoder).encode(item);
            verify(primitiveEncoder).writeBytes(new byte[item.size()], bytesToWriteInto(), offset);
            offset += item.size();
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelValuePackEncoderImpl(null, suplaChannelValueEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaChannelValueEncoderIsNull() {
        new SuplaChannelValuePackEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaChannelValuePack> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelValuePack getProto() {
        return proto;
    }
}
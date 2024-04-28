package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATIONPACK_MAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelPackBEncoderImplTest extends EncoderTest<SuplaChannelPackB> {
    @InjectMocks
    SuplaChannelPackBEncoderImpl encoder;
    @Mock
    SuplaChannelBEncoder suplaChannelBEncoder;

    SuplaChannelPackB proto;

    @Before
    public void setUp() {
        final int count = RANDOM_SUPLA.nextPositiveInt(SUPLA_LOCATIONPACK_MAXCOUNT);
        proto = new SuplaChannelPackB(
            count,
            RANDOM_SUPLA.nextPositiveInt(),
            Stream.generate(() -> RANDOM_SUPLA.nextObject(SuplaChannelB.class))
                .limit(count)
                .toArray(SuplaChannelB[]::new));
        for (SuplaChannelB channel : proto.channels) {
            given(suplaChannelBEncoder.encode(channel)).willReturn(new byte[channel.size()]);
        }
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelPackB proto) {
        int offset = 0;
        verify(primitiveEncoder).writeInteger(proto.count, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.totalLeft, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        for (SuplaChannelB channel : proto.channels) {
            verify(suplaChannelBEncoder).encode(channel);
            verify(primitiveEncoder).writeBytes(new byte[channel.size()], bytesToWriteInto(), offset);
            offset += channel.size();
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelPackBEncoderImpl(null, suplaChannelBEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaChannelBEncoderIsNull() {
        new SuplaChannelPackBEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaChannelPackB> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelPackB getProto() {
        return proto;
    }
}
package pl.grzeslowski.jsupla.protocol.api.encoders.sc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelGroupRelationEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;

import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelGroupRelationPackEncoderImplTest extends EncoderTest<SuplaChannelGroupRelationPack> {
    @InjectMocks
    SuplaChannelGroupRelationPackEncoderImpl encoder;
    @Mock
    SuplaChannelGroupRelationEncoder suplaChannelGroupRelationEncoder;

    SuplaChannelGroupRelationPack proto;

    @Before
    public void setUp() {
        final int count = RANDOM_SUPLA.nextPositiveInt(100);
        proto = new SuplaChannelGroupRelationPack(
            count,
            RANDOM_SUPLA.nextPositiveInt(),
            Stream.generate(() -> RANDOM_SUPLA.nextObject(SuplaChannelGroupRelation.class))
                .limit(count)
                .toArray(SuplaChannelGroupRelation[]::new));
        stream(proto.items).forEach(item ->
            given(suplaChannelGroupRelationEncoder.encode(item))
                .willReturn(new byte[item.size()]));
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelGroupRelationPack proto) {
        int offset = 0;
        verify(primitiveEncoder).writeInteger(proto.count, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.totalLeft, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        for (SuplaChannelGroupRelation item : proto.items) {
            verify(primitiveEncoder).writeBytes(new byte[item.size()], bytesToWriteInto(), offset);
            verify(suplaChannelGroupRelationEncoder).encode(item);
            offset += item.size();
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelGroupRelationPackEncoderImpl(null, suplaChannelGroupRelationEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaChannelGroupRelationEncoderIsNull() {
        new SuplaChannelGroupRelationPackEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaChannelGroupRelationPack> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelGroupRelationPack getProto() {
        return proto;
    }
}
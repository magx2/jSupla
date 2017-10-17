package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class FirmwareUpdateUrlResultSerializerImplTest
        extends SerializerTest<FirmwareUpdateUrlResult, SuplaFirmwareUpdateUrlResult> {
    @InjectMocks FirmwareUpdateUrlResultSerializerImpl serializer;
    @Mock FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer;

    @Override
    protected void then(final FirmwareUpdateUrlResult entity, final SuplaFirmwareUpdateUrlResult proto) {
        assertThat(proto.exists).isEqualTo((byte) (entity.isExists() ? 1 : 0));
        verify(firmwareUpdateUrlSerializer).serialize(entity.getFirmwareUpdateUrl());
    }

    @Override
    protected Serializer<FirmwareUpdateUrlResult, SuplaFirmwareUpdateUrlResult> serializer() {
        return serializer;
    }

    @Override
    protected Class<FirmwareUpdateUrlResult> entityClass() {
        return FirmwareUpdateUrlResult.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenFirmwareUpdateUrlSerializerIsNull() {
        new FirmwareUpdateUrlResultSerializerImpl(null);
    }
}
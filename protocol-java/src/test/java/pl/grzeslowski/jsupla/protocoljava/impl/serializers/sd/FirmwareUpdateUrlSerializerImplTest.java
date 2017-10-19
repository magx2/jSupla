package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

@SuppressWarnings("WeakerAccess")
public class FirmwareUpdateUrlSerializerImplTest extends SerializerTest<FirmwareUpdateUrl, SuplaFirmwareUpdateUrl> {
    @InjectMocks FirmwareUpdateUrlSerializerImpl serializer;
    @Mock StringSerializer stringSerializer;

    @Override
    protected FirmwareUpdateUrl given() {
        givenStringSerializer(stringSerializer);
        return super.given();
    }

    @Override
    protected void then(final FirmwareUpdateUrl entity, final SuplaFirmwareUpdateUrl proto) {
        assertThat((int) proto.availableProtocols).isEqualTo(entity.getAvailableProtocols());
        verify(stringSerializer).serialize(entity.getHost(), SUPLA_URL_HOST_MAXSIZE);
        assertThat(proto.port).isEqualTo(entity.getPort());
        verify(stringSerializer).serialize(entity.getPath(), SUPLA_URL_PATH_MAXSIZE);
    }

    @Override
    protected Serializer<FirmwareUpdateUrl, SuplaFirmwareUpdateUrl> serializer() {
        return serializer;
    }

    @Override
    protected Class<FirmwareUpdateUrl> entityClass() {
        return FirmwareUpdateUrl.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringSerializerIsNull() {
        new FirmwareUpdateUrlSerializerImpl(null);
    }
}
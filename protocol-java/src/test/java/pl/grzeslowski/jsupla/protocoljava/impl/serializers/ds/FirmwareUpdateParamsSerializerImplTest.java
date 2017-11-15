package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class FirmwareUpdateParamsSerializerImplTest
        extends SerializerTest<FirmwareUpdateParams, SuplaFirmwareUpdateParams> {
    @InjectMocks FirmwareUpdateParamsSerializerImpl serializer;

    @Override
    protected void then(final FirmwareUpdateParams entity, final SuplaFirmwareUpdateParams proto) {
        assertThat(proto.platform).isEqualTo((byte) entity.getPlatform());
        assertThat(proto.param1).isEqualTo((short) entity.getParam1());
        assertThat(proto.param2).isEqualTo((short) entity.getParam2());
        assertThat(proto.param3).isEqualTo((short) entity.getParam3());
        assertThat(proto.param4).isEqualTo((short) entity.getParam4());
    }

    @Override
    protected Serializer<FirmwareUpdateParams, SuplaFirmwareUpdateParams> serializer() {
        return serializer;
    }

    @Override
    protected Class<FirmwareUpdateParams> entityClass() {
        return FirmwareUpdateParams.class;
    }
}
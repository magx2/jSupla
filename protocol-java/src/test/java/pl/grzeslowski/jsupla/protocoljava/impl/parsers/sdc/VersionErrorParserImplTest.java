package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class VersionErrorParserImplTest extends AbstractParserTest<VersionError, SuplaVersionError> {
    @InjectMocks
    VersionErrorParserImpl parser;

    @Override
    protected void then(final VersionError entity, final SuplaVersionError supla) {
        assertThat(entity.getServerVersionMin()).isEqualTo(supla.serverVersionMin);
        assertThat(entity.getServerVersion()).isEqualTo(supla.serverVersion);
    }

    @Override
    protected Parser<VersionError, SuplaVersionError> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaVersionError> classToTest() {
        return SuplaVersionError.class;
    }
}
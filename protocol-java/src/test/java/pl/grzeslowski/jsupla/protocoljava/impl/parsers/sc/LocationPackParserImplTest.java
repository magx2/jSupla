package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class LocationPackParserImplTest extends ParserTest<LocationPack, SuplaLocationPack> {
    @InjectMocks LocationPackParserImpl parser;
    @Mock LocationParser locationParser;

    @Override
    protected SuplaLocationPack given() {
        BDDMockito.given(locationParser.parse(any()))
                .willReturn(RANDOM_ENTITY.nextObject(Location.class));
        return super.given();
    }

    @Override
    protected void then(final LocationPack entity, final SuplaLocationPack supla) {
        assertThat(entity.getTotalLeft()).isEqualTo(supla.totalLeft);
        Arrays.stream(supla.locations)
                .forEach(location -> verify(locationParser).parse(location));
        verifyNoMoreInteractions(locationParser);
    }

    @Override
    protected Parser<LocationPack, SuplaLocationPack> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaLocationPack> classToTest() {
        return SuplaLocationPack.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenLocationParserIsNull() {
        new LocationPackParserImpl(null);
    }
}
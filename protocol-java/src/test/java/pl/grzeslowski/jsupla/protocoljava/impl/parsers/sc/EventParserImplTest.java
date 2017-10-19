package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class EventParserImplTest extends ParserTest<Event, SuplaEvent> {
    @InjectMocks EventParserImpl parser;
    @Mock StringParser stringParser;

    @Override
    protected SuplaEvent given() {
        final SuplaEvent supla = super.given();
        BDDMockito.given(stringParser.parse(supla.senderName))
                .willReturn(RANDOM_ENTITY.nextObject(String.class).substring(0, 5));
        return supla;
    }

    @Override
    protected void then(final Event entity, final SuplaEvent supla) {
        assertThat(entity.getEvent()).isEqualTo(supla.event);
        assertThat(entity.getChannelId()).isEqualTo(supla.channelId);
        assertThat(entity.getDurationMs()).isEqualTo(supla.durationMs);
        assertThat(entity.getSenderId()).isEqualTo(supla.senderId);
        verify(stringParser).parse(supla.senderName);
    }

    @Override
    protected Parser<Event, SuplaEvent> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaEvent> classToTest() {
        return SuplaEvent.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new EventParserImpl(null);
    }
}
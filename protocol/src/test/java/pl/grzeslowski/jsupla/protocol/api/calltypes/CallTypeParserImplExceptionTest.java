package pl.grzeslowski.jsupla.protocol.api.calltypes;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CallTypeParserImplExceptionTest {
    private final CallTypeParserImpl callTypeParser = new CallTypeParserImpl();

    @Test
    public void shouldReturnEmptyWhenThereIsNoCallType() throws Exception {

        // given
        long notExistingCallType = Long.MAX_VALUE;

        // when
        final Optional<CallType> callType = callTypeParser.parse(notExistingCallType);

        // then
        assertThat(callType).isEmpty();
    }
}
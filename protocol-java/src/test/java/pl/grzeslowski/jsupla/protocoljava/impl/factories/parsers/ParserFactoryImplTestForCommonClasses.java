package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.cs.ClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.dcs.DeviceClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ds.DeviceServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sc.ServerClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sd.ServerDeviceParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sdc.ServerDeviceClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class ParserFactoryImplTestForCommonClasses {
    @InjectMocks ParserFactoryImpl factory;

    @Mock ClientServerParserFactory clientServerParserFactory;
    @Mock DeviceClientServerParserFactory deviceClientServerParserFactory;
    @Mock DeviceServerParserFactory deviceServerParserFactory;
    @Mock ServerClientParserFactory serverClientParserFactory;
    @Mock ServerDeviceParserFactory serverDeviceParserFactory;
    @Mock ServerDeviceClientParserFactory serverDeviceClientParserFactory;

    @Mock DeviceChannelParser deviceChannelParser;
    @Mock DeviceChannelBParser deviceChannelBParser;
    @Mock FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    @Mock ChannelValueParser channelValueParser;
    @Mock TimevalParser timevalParser;

    final Class<? extends Proto> clazzToTest;
    final BiConsumer<Object, MockFactories> verifier;

    public ParserFactoryImplTestForCommonClasses(final Class<? extends Proto> clazzToTest,
                                                 final BiConsumer<Object, MockFactories> verifier) {
        this.clazzToTest = clazzToTest;
        this.verifier = verifier;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {SuplaDeviceChannel.class, (BiConsumer<Parser<Entity, Proto>, MockFactories>)
                                                   ParserFactoryImplTestForCommonClasses::verifyDeviceChannelParser},
                {SuplaDeviceChannelB.class, (BiConsumer<Parser<Entity, Proto>, MockFactories>)
                                                    ParserFactoryImplTestForCommonClasses::verifyDeviceChannelBParser},
                {SuplaFirmwareUpdateUrl.class,
                        (BiConsumer<Parser<Entity, Proto>, MockFactories>)
                                ParserFactoryImplTestForCommonClasses::verifyFirmwareUpdateUrlParser},
                {SuplaChannelValue.class, (BiConsumer<Parser<Entity, Proto>, MockFactories>)
                                                  ParserFactoryImplTestForCommonClasses::verifyChannelValueParser},
                {SuplaTimeval.class, (BiConsumer<Parser<Entity, Proto>, MockFactories>)
                                             ParserFactoryImplTestForCommonClasses::verifyTimevalParser}
        });
    }

    static void verifyDeviceChannelParser(Parser<? super Entity, ? super Proto> parser, MockFactories mockFactories) {
        assertThat(parser).isEqualTo(mockFactories.deviceChannelParser);
    }

    static void verifyDeviceChannelBParser(Parser<Entity, Proto> parser, MockFactories mockFactories) {
        assertThat(parser).isEqualTo(mockFactories.deviceChannelBParser);
    }

    static void verifyFirmwareUpdateUrlParser(Parser<Entity, Proto> parser, MockFactories mockFactories) {
        assertThat(parser).isEqualTo(mockFactories.firmwareUpdateUrlParser);
    }

    static void verifyChannelValueParser(Parser<Entity, Proto> parser, MockFactories mockFactories) {
        assertThat(parser).isEqualTo(mockFactories.channelValueParser);
    }

    static void verifyTimevalParser(Parser<Entity, Proto> parser, MockFactories mockFactories) {
        assertThat(parser).isEqualTo(mockFactories.timevalParser);
    }

    static void verifyDataPacketParser(Parser<Entity, Proto> parser, MockFactories mockFactories) {
        assertThat(parser).isEqualTo(mockFactories.timevalParser);
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldUseProperParser() throws Exception {

        // given
        final Proto proto = RANDOM_SUPLA.nextObject(clazzToTest);

        // when
        final Parser<? extends Entity, ? extends Proto> parser = factory.getParser(proto);

        // then
        verifier.accept(parser, new MockFactories(deviceChannelParser,
                                                         deviceChannelBParser,
                                                         firmwareUpdateUrlParser,
                                                         channelValueParser,
                                                         timevalParser));
    }

    private static class MockFactories {
        final DeviceChannelParser deviceChannelParser;
        final DeviceChannelBParser deviceChannelBParser;
        final FirmwareUpdateUrlParser firmwareUpdateUrlParser;
        final ChannelValueParser channelValueParser;
        final TimevalParser timevalParser;

        private MockFactories(final DeviceChannelParser deviceChannelParser,
                              final DeviceChannelBParser deviceChannelBParser,
                              final FirmwareUpdateUrlParser firmwareUpdateUrlParser,
                              final ChannelValueParser channelValueParser,
                              final TimevalParser timevalParser) {
            this.deviceChannelParser = deviceChannelParser;
            this.deviceChannelBParser = deviceChannelBParser;
            this.firmwareUpdateUrlParser = firmwareUpdateUrlParser;
            this.channelValueParser = channelValueParser;
            this.timevalParser = timevalParser;
        }
    }
}
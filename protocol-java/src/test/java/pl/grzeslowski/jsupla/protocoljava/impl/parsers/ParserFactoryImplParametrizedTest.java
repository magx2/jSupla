package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ClientServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.DeviceClientServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ServerClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ServerDeviceParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.ServerDeviceClientParser;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class ParserFactoryImplParametrizedTest {
    static final Collection<Class<? extends ClientServer>> CLIENT_SERVER_CLASSES = Arrays.asList(
            SuplaChannelNewValue.class,
            SuplaChannelNewValueB.class,
            SuplaRegisterClient.class,
            SuplaRegisterClientB.class
    );
    static final Collection<Class<? extends DeviceClientServer>> DEVICE_CLIENT_SERVER_CLASSES = Arrays.asList(
            SuplaPingServer.class,
            SuplaSetActivityTimeout.class
    );
    static final Collection<Class<? extends DeviceServer>> DEVICE_SERVER_CLASSES = Arrays.asList(
            SuplaChannelNewValueResult.class,
            SuplaDeviceChannelValue.class,
            SuplaFirmwareUpdateParams.class,
            SuplaRegisterDevice.class,
            SuplaRegisterDeviceB.class,
            SuplaRegisterDeviceC.class
    );
    static final Collection<Class<? extends ServerClient>> SERVER_CLIENT_CLASSES = Arrays.asList(
            SuplaChannel.class,
            SuplaChannelPack.class,
            pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class,
            SuplaEvent.class,
            SuplaLocation.class,
            SuplaLocationPack.class,
            SuplaRegisterClientResult.class
    );
    static final Collection<Class<? extends ServerDevice>> SERVER_DEVICE_CLASSES = Arrays.asList(
            pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class,
            SuplaFirmwareUpdateUrlResult.class,
            SuplaRegisterDeviceResult.class
    );
    static final Collection<Class<? extends ServerDeviceClient>> SERVER_DEVICE_CLIENT_CLASSES = Arrays.asList(
            SuplaGetVersionResult.class,
            SuplaPingServerResultClient.class,
            SuplaSetActivityTimeoutResult.class,
            SuplaVersionError.class
    );

    @InjectMocks ParserImpl parser;

    @Mock ClientServerParser clientServerParserFactory;
    @Mock DeviceClientServerParser deviceClientServerParserFactory;
    @Mock DeviceServerParser deviceServerParserFactory;
    @Mock ServerClientParser serverClientParserFactory;
    @Mock ServerDeviceParser serverDeviceParserFactory;
    @Mock ServerDeviceClientParser serverDeviceClientParserFactory;

    @Mock DeviceChannelParser deviceChannelParser;
    @Mock DeviceChannelBParser deviceChannelBParser;
    @Mock FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    @Mock ChannelValueParser channelValueParser;
    @Mock TimevalParser timevalParser;

    final Collection<Class<? extends Proto>> protosToTest;
    final BiConsumer<Proto, MockFactories> verify;

    public ParserFactoryImplParametrizedTest(final Collection<Class<? extends Proto>> protosToTest,
                                             final BiConsumer<Proto, MockFactories> verify) {
        this.protosToTest = protosToTest;
        this.verify = verify;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CLIENT_SERVER_CLASSES, (BiConsumer<Proto, MockFactories>)
                                                ParserFactoryImplParametrizedTest::invokeOnlyClientServerParserFactory},
                {DEVICE_CLIENT_SERVER_CLASSES,
                        (BiConsumer<Proto, MockFactories>)
                                ParserFactoryImplParametrizedTest::invokeOnlyDeviceClientServerParserFactory},
                {DEVICE_SERVER_CLASSES, (BiConsumer<Proto, MockFactories>)
                                                ParserFactoryImplParametrizedTest::invokeOnlyDeviceServerParserFactory},
                {SERVER_CLIENT_CLASSES, (BiConsumer<Proto, MockFactories>)
                                                ParserFactoryImplParametrizedTest::invokeOnlyServerClientParserFactory},
                {SERVER_DEVICE_CLASSES, (BiConsumer<Proto, MockFactories>)
                                                ParserFactoryImplParametrizedTest::invokeOnlyServerDeviceParserFactory},
                {SERVER_DEVICE_CLIENT_CLASSES,
                        (BiConsumer<Proto, MockFactories>)
                                ParserFactoryImplParametrizedTest::invokeOnlyServerDeviceClientParserFactory},
        });
    }

    @SuppressWarnings("unchecked")
    static void invokeOnlyClientServerParserFactory(Proto proto, MockFactories mockFactories) {
        verify(mockFactories.clientServerParserFactory).parse(proto);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    @SuppressWarnings("unchecked")
    static void invokeOnlyDeviceClientServerParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verify(mockFactories.deviceClientServerParserFactory).parse(proto);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    @SuppressWarnings("unchecked")
    static void invokeOnlyDeviceServerParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verify(mockFactories.deviceServerParserFactory).parse(proto);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    @SuppressWarnings("unchecked")
    static void invokeOnlyServerClientParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verify(mockFactories.serverClientParserFactory).parse(proto);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    @SuppressWarnings("unchecked")
    static void invokeOnlyServerDeviceParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verify(mockFactories.serverDeviceParserFactory).parse(proto);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    @SuppressWarnings("unchecked")
    static void invokeOnlyServerDeviceClientParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verify(mockFactories.serverDeviceClientParserFactory).parse(proto);
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAndUserProperFactoryForAllClasses() throws Exception {
        protosToTest.stream()
                .map(clazz -> RANDOM_SUPLA.nextObject(clazz))
                .forEach(this::shouldFindAndUserProperFactory);
    }

    void shouldFindAndUserProperFactory(Proto proto) {

        // when
        parser.parse(proto);

        // then
        verify.accept(proto, new MockFactories(
                                                      clientServerParserFactory,
                                                      deviceClientServerParserFactory,
                                                      deviceServerParserFactory,
                                                      serverClientParserFactory,
                                                      serverDeviceParserFactory,
                                                      serverDeviceClientParserFactory
        ));
    }

    private static class MockFactories {
        final ClientServerParser clientServerParserFactory;
        final DeviceClientServerParser deviceClientServerParserFactory;
        final DeviceServerParser deviceServerParserFactory;
        final ServerClientParser serverClientParserFactory;
        final ServerDeviceParser serverDeviceParserFactory;
        final ServerDeviceClientParser serverDeviceClientParserFactory;

        private MockFactories(final ClientServerParser clientServerParserFactory,
                              final DeviceClientServerParser deviceClientServerParserFactory,
                              final DeviceServerParser deviceServerParserFactory,
                              final ServerClientParser serverClientParserFactory,
                              final ServerDeviceParser serverDeviceParserFactory,
                              final ServerDeviceClientParser serverDeviceClientParserFactory) {
            this.clientServerParserFactory = clientServerParserFactory;
            this.deviceClientServerParserFactory = deviceClientServerParserFactory;
            this.deviceServerParserFactory = deviceServerParserFactory;
            this.serverClientParserFactory = serverClientParserFactory;
            this.serverDeviceParserFactory = serverDeviceParserFactory;
            this.serverDeviceClientParserFactory = serverDeviceClientParserFactory;
        }
    }
}
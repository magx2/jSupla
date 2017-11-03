package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers;

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
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.cs.ClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.dcs.DeviceClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ds.DeviceServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sc.ServerClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sd.ServerDeviceParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sdc.ServerDeviceClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;

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

    static void invokeOnlyClientServerParserFactory(Proto proto, MockFactories mockFactories) {
        verify(mockFactories.clientServerParserFactory).getParser((ClientServer) proto);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    static void invokeOnlyDeviceClientServerParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verify(mockFactories.deviceClientServerParserFactory).getParser((DeviceClientServer) proto);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    static void invokeOnlyDeviceServerParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verify(mockFactories.deviceServerParserFactory).getParser((DeviceServer) proto);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    static void invokeOnlyServerClientParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verify(mockFactories.serverClientParserFactory).getParser((ServerClient) proto);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    static void invokeOnlyServerDeviceParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verify(mockFactories.serverDeviceParserFactory).getParser((ServerDevice) proto);
        verifyNoMoreInteractions(mockFactories.serverDeviceClientParserFactory);
    }

    static void invokeOnlyServerDeviceClientParserFactory(Proto proto, MockFactories mockFactories) {
        verifyNoMoreInteractions(mockFactories.clientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceClientServerParserFactory);
        verifyNoMoreInteractions(mockFactories.deviceServerParserFactory);
        verifyNoMoreInteractions(mockFactories.serverClientParserFactory);
        verifyNoMoreInteractions(mockFactories.serverDeviceParserFactory);
        verify(mockFactories.serverDeviceClientParserFactory).getParser((ServerDeviceClient) proto);
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
        factory.getParser(proto);

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
        final ClientServerParserFactory clientServerParserFactory;
        final DeviceClientServerParserFactory deviceClientServerParserFactory;
        final DeviceServerParserFactory deviceServerParserFactory;
        final ServerClientParserFactory serverClientParserFactory;
        final ServerDeviceParserFactory serverDeviceParserFactory;
        final ServerDeviceClientParserFactory serverDeviceClientParserFactory;

        private MockFactories(final ClientServerParserFactory clientServerParserFactory,
                              final DeviceClientServerParserFactory deviceClientServerParserFactory,
                              final DeviceServerParserFactory deviceServerParserFactory,
                              final ServerClientParserFactory serverClientParserFactory,
                              final ServerDeviceParserFactory serverDeviceParserFactory,
                              final ServerDeviceClientParserFactory serverDeviceClientParserFactory) {
            this.clientServerParserFactory = clientServerParserFactory;
            this.deviceClientServerParserFactory = deviceClientServerParserFactory;
            this.deviceServerParserFactory = deviceServerParserFactory;
            this.serverClientParserFactory = serverClientParserFactory;
            this.serverDeviceParserFactory = serverDeviceParserFactory;
            this.serverDeviceClientParserFactory = serverDeviceClientParserFactory;
        }
    }
}
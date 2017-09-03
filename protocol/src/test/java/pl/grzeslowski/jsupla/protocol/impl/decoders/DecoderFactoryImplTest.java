package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.dcs.SuplaPingServerDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.dcs.SuplaSetActivityTimeoutDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.FirmwareUpdateParamsDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaChannelNewValueResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaDeviceChannelBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaDeviceChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaDeviceChannelValueDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceCDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelPackDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaEventDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaLocationDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaLocationPackDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaRegisterClientResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.FirmwareUpdateUrlDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.FirmwareUpdateUrlResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaRegisterDeviceResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaGetVersionResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaPingServerResultClientDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaSetActivityTimeoutResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaVersionErrorDecoderImpl;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static pl.grzeslowski.jsupla.protocol.common.RandomBean.RANDOM_BEAN;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class DecoderFactoryImplTest {
    private final DecoderFactoryImpl decoderFactory = new DecoderFactoryImpl(mock(PrimitiveDecoder.class));

    private final ProtoWithSize proto;
    private final Class<Decoder<?>> decoderClass;

    // for generating
    private static int newInt = 1_000;
    private static long newUnsignedInt = newInt * 10;
    private static byte newByte = Byte.MIN_VALUE;
    private static short newUnsignedByte = 0;

    public DecoderFactoryImplTest(final ProtoWithSize proto, final Class<Decoder<?>> decoderClass) {
        this.proto = proto;
        this.decoderClass = decoderClass;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // cs
                {RANDOM_BEAN.nextObject(SuplaChannelNewValueB.class), SuplaChannelNewValueBDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaChannelNewValue.class), SuplaChannelNewValueDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaRegisterClientB.class), SuplaRegisterClientBDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaRegisterClient.class), SuplaRegisterClientDecoderImpl.class},

                // dcs
                {RANDOM_BEAN.nextObject(SuplaPingServer.class), SuplaPingServerDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaSetActivityTimeout.class), SuplaSetActivityTimeoutDecoderImpl.class},

                // ds                                   
                {RANDOM_BEAN.nextObject(FirmwareUpdateParams.class), FirmwareUpdateParamsDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaChannelNewValueResult.class), SuplaChannelNewValueResultDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaDeviceChannelB.class), SuplaDeviceChannelBDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaDeviceChannel.class), SuplaDeviceChannelDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaDeviceChannelValue.class), SuplaDeviceChannelValueDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaRegisterDeviceB.class), SuplaRegisterDeviceBDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaRegisterDeviceC.class), SuplaRegisterDeviceCDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaRegisterDevice.class), SuplaRegisterDeviceDecoderImpl.class},

                // sc 
                {RANDOM_BEAN.nextObject(SuplaChannel.class), SuplaChannelDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaChannelPack.class), SuplaChannelPackDecoderImpl.class},
                {RANDOM_BEAN.nextObject(pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class),
                        pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelValueDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaEvent.class), SuplaEventDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaLocation.class), SuplaLocationDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaLocationPack.class), SuplaLocationPackDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaRegisterClientResult.class), SuplaRegisterClientResultDecoderImpl.class},

                // sd
                {RANDOM_BEAN.nextObject(FirmwareUpdateUrl.class), FirmwareUpdateUrlDecoderImpl.class},
                {RANDOM_BEAN.nextObject(FirmwareUpdateUrlResult.class), FirmwareUpdateUrlResultDecoderImpl.class},
                {RANDOM_BEAN.nextObject(pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class),
                        pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaChannelNewValueDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaRegisterDeviceResult.class), SuplaRegisterDeviceResultDecoderImpl.class},

                // sdc 
                {RANDOM_BEAN.nextObject(SuplaGetVersionResult.class), SuplaGetVersionResultDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaPingServerResultClient.class),
                        SuplaPingServerResultClientDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaSetActivityTimeoutResult.class),
                        SuplaSetActivityTimeoutResultDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaVersionError.class), SuplaVersionErrorDecoderImpl.class},

                // common 
                {RANDOM_BEAN.nextObject(SuplaChannelValue.class), SuplaChannelValueDecoderImpl.class},
                {RANDOM_BEAN.nextObject(SuplaDataPacket.class), SuplaDataPacketDecoderImpl.class},
                {RANDOM_BEAN.nextObject(Timeval.class), TimevalDecoderImpl.class}
        });
    }

    @Test
    public void shouldFindProperDecoderForProto() throws Exception {

        // when
        final Decoder<ProtoWithSize> decoder = decoderFactory.getDecoder(proto);

        // then
        assertThat(decoder).isOfAnyClassIn(decoderClass);
    }

    @Test
    public void shouldFindProperDecoderForCallType() throws Exception {

        // given
        if (!(proto instanceof ProtoWithCallType)) {
            return; // do not need to test this
        }

        ProtoWithCallType protoWithCallType = (ProtoWithCallType) proto;

        // when
        final Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(protoWithCallType.callType());

        // then
        assertThat(decoder).isOfAnyClassIn(decoderClass);
    }

    private static int newInt() {
        return newInt++;
    }

    private static long newUnsignedInt() {
        return newUnsignedInt++;
    }

    private static byte newByte() {
        return newByte++;
    }

    private static short newUnsignedByte() {
        return newUnsignedByte++;
    }
}
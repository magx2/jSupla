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
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class DecoderFactoryImplTest {
    static final SuplaChannelNewValueB SUPLA_CHANNEL_NEW_VALUE_B =
            new SuplaChannelNewValueB(newInt(), new byte[SUPLA_CHANNELVALUE_SIZE]);
    static final SuplaChannelNewValue SUPLA_CHANNEL_NEW_VALUE =
            new SuplaChannelNewValue(newByte(), new byte[SUPLA_CHANNELVALUE_SIZE]);
    static final SuplaRegisterClientB SUPLA_REGISTER_CLIENT_B =
            new SuplaRegisterClientB(
                                            newInt(),
                                            new byte[SUPLA_ACCESSID_PWD_MAXSIZE],
                                            new byte[SUPLA_GUID_SIZE],
                                            new byte[SUPLA_CLIENT_NAME_MAXSIZE],
                                            new byte[SUPLA_SOFTVER_MAXSIZE],
                                            new byte[SUPLA_SERVER_NAME_MAXSIZE]);
    static final SuplaRegisterClient SUPLA_REGISTER_CLIENT =
            new SuplaRegisterClient(
                                           newInt(),
                                           new byte[SUPLA_ACCESSID_PWD_MAXSIZE],
                                           new byte[SUPLA_GUID_SIZE],
                                           new byte[SUPLA_CLIENT_NAME_MAXSIZE],
                                           new byte[SUPLA_SOFTVER_MAXSIZE]);
    static final SuplaPingServer SUPLA_PING_SERVER = new SuplaPingServer(new Timeval(newInt(), newInt()));
    static final SuplaSetActivityTimeout SUPLA_SET_ACTIVITY_TIMEOUT = new SuplaSetActivityTimeout(newUnsignedByte());
    static final FirmwareUpdateParams FIRMWARE_UPDATE_PARAMS =
            new FirmwareUpdateParams(newByte(), newInt(), newInt(), newInt(), newInt());
    static final SuplaChannelNewValueResult SUPLA_CHANNEL_NEW_VALUE_RESULT =
            new SuplaChannelNewValueResult(newUnsignedByte(), newInt(), newByte());
    static final SuplaDeviceChannelB SUPLA_DEVICE_CHANNEL_B =
            new SuplaDeviceChannelB(newUnsignedByte(), newInt(), newInt(), newInt(), new byte[SUPLA_CHANNELVALUE_SIZE]);
    static final SuplaDeviceChannel SUPLA_DEVICE_CHANNEL =
            new SuplaDeviceChannel(newUnsignedByte(), newInt(), new byte[SUPLA_CHANNELVALUE_SIZE]);
    static final SuplaDeviceChannelValue SUPLA_DEVICE_CHANNEL_VALUE =
            new SuplaDeviceChannelValue(newUnsignedByte(), new byte[SUPLA_CHANNELVALUE_SIZE]);
    static final SuplaRegisterDeviceB SUPLA_REGISTER_DEVICE_B =
            new SuplaRegisterDeviceB(
                                            newInt(),
                                            new byte[SUPLA_LOCATION_PWD_MAXSIZE],
                                            new byte[SUPLA_GUID_SIZE],
                                            new byte[SUPLA_DEVICE_NAME_MAXSIZE],
                                            new byte[SUPLA_SOFTVER_MAXSIZE],
                                            (short) 0,
                                            new SuplaDeviceChannelB[0]);
    static final SuplaRegisterDeviceC SUPLA_REGISTER_DEVICE_C =
            new SuplaRegisterDeviceC(
                                            newInt(),
                                            new byte[SUPLA_LOCATION_PWD_MAXSIZE],
                                            new byte[SUPLA_GUID_SIZE],
                                            new byte[SUPLA_DEVICE_NAME_MAXSIZE],
                                            new byte[SUPLA_SOFTVER_MAXSIZE],
                                            new byte[SUPLA_SERVER_NAME_MAXSIZE],
                                            (short) 0,
                                            new SuplaDeviceChannelB[0]);
    static final SuplaRegisterDevice SUPLA_REGISTER_DEVICE =
            new SuplaRegisterDevice(
                                           newInt(),
                                           new byte[SUPLA_LOCATION_PWD_MAXSIZE],
                                           new byte[SUPLA_GUID_SIZE],
                                           new byte[SUPLA_DEVICE_NAME_MAXSIZE],
                                           new byte[SUPLA_SOFTVER_MAXSIZE],
                                           (short) 0,
                                           new SuplaDeviceChannel[0]);
    static final SuplaChannel SUPLA_CHANNEL =
            new SuplaChannel(
                                    newByte(),
                                    newInt(),
                                    newInt(),
                                    newInt(),
                                    newByte(),
                                    new SuplaChannelValue(
                                                                 new byte[SUPLA_CHANNELVALUE_SIZE],
                                                                 new byte[SUPLA_CHANNELVALUE_SIZE]),
                                    0,
                                    new byte[0]);
    static final SuplaChannelPack SUPLA_CHANNEL_PACK = new SuplaChannelPack(0, newInt(), new SuplaChannel[0]);
    // @formatter:off
    static final pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue SUPLA_CHANNEL_VALUE =
            new pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue(
                                                      newByte(),
                                                      newInt(),
                                                      newByte(),
                                                      new SuplaChannelValue(
                                                                                   new byte[SUPLA_CHANNELVALUE_SIZE],
                                                                                   new byte[SUPLA_CHANNELVALUE_SIZE]));
    // @formatter:on
    static final SuplaEvent SUPLA_EVENT =
            new SuplaEvent(newInt(), newInt(), newUnsignedInt(), newInt(), 0, new byte[0]);
    static final SuplaLocation SUPLA_LOCATION = new SuplaLocation(newByte(), newInt(), 0, new byte[0]);
    static final SuplaLocationPack SUPLA_LOCATION_PACK = new SuplaLocationPack(0, newInt(), new SuplaLocation[0]);
    static final SuplaRegisterClientResult SUPLA_REGISTER_CLIENT_RESULT =
            new SuplaRegisterClientResult(
                                                 newInt(),
                                                 newInt(),
                                                 newInt(),
                                                 newInt(),
                                                 newUnsignedByte(),
                                                 newUnsignedByte(),
                                                 newUnsignedByte());
    static final FirmwareUpdateUrl FIRMWARE_UPDATE_URL =
            new FirmwareUpdateUrl(
                                         newByte(),
                                         new byte[SUPLA_URL_HOST_MAXSIZE],
                                         newInt(),
                                         new byte[SUPLA_URL_PATH_MAXSIZE]);
    static final FirmwareUpdateUrlResult FIRMWARE_UPDATE_URL_RESULT =
            new FirmwareUpdateUrlResult(
                                               newByte(),
                                               new FirmwareUpdateUrl(
                                                                            newByte(),
                                                                            new byte[SUPLA_URL_HOST_MAXSIZE],
                                                                            newInt(),
                                                                            new byte[SUPLA_URL_PATH_MAXSIZE]));
    // @formatter:off
    static final pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue SUPLA_CHANNEL_NEW_VALUE1 =
            new pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue(
                                                                                     newInt(),
                                                                                     newUnsignedByte(),
                                                                                     newUnsignedInt(),
                                                                                     new byte[SUPLA_CHANNELVALUE_SIZE]);
    // @formatter:on
    static final SuplaRegisterDeviceResult SUPLA_REGISTER_DEVICE_RESULT =
            new SuplaRegisterDeviceResult(newInt(), newByte(), (byte) 100, (byte) 99);
    static final SuplaGetVersionResult SUPLA_GET_VERSION_RESULT =
            new SuplaGetVersionResult(newUnsignedByte(), newUnsignedByte(), new byte[SUPLA_SOFTVER_MAXSIZE]);
    static final SuplaPingServerResultClient SUPLA_PING_SERVER_RESULT_CLIENT =
            new SuplaPingServerResultClient(
                                                   new Timeval(newInt(), newInt()));
    static final SuplaSetActivityTimeoutResult SUPLA_SET_ACTIVITY_TIMEOUT_RESULT =
            new SuplaSetActivityTimeoutResult(newUnsignedByte(), newUnsignedByte(), newUnsignedByte());
    static final SuplaVersionError SUPLA_VERSION_ERROR = new SuplaVersionError(newUnsignedByte(), newUnsignedByte());
    static final SuplaChannelValue SUPLA_CHANNEL_VALUE1 =
            new SuplaChannelValue(new byte[SUPLA_CHANNELVALUE_SIZE], new byte[SUPLA_CHANNELVALUE_SIZE]);
    static final SuplaDataPacket SUPLA_DATA_PACKET =
            new SuplaDataPacket(newUnsignedByte(), newUnsignedInt(), newUnsignedInt(), 0, new byte[0]);
    static final Timeval TIMEVAL = new Timeval(newInt(), newInt());

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
                {SUPLA_CHANNEL_NEW_VALUE_B, SuplaChannelNewValueBDecoderImpl.class},
                {SUPLA_CHANNEL_NEW_VALUE, SuplaChannelNewValueDecoderImpl.class},
                {SUPLA_REGISTER_CLIENT_B, SuplaRegisterClientBDecoderImpl.class},
                {SUPLA_REGISTER_CLIENT, SuplaRegisterClientDecoderImpl.class},

                // dcs
                {SUPLA_PING_SERVER, SuplaPingServerDecoderImpl.class},
                {SUPLA_SET_ACTIVITY_TIMEOUT, SuplaSetActivityTimeoutDecoderImpl.class},

                // ds                                   
                {FIRMWARE_UPDATE_PARAMS, FirmwareUpdateParamsDecoderImpl.class},
                {SUPLA_CHANNEL_NEW_VALUE_RESULT, SuplaChannelNewValueResultDecoderImpl.class},
                {SUPLA_DEVICE_CHANNEL_B, SuplaDeviceChannelBDecoderImpl.class},
                {SUPLA_DEVICE_CHANNEL, SuplaDeviceChannelDecoderImpl.class},
                {SUPLA_DEVICE_CHANNEL_VALUE, SuplaDeviceChannelValueDecoderImpl.class},
                {SUPLA_REGISTER_DEVICE_B, SuplaRegisterDeviceBDecoderImpl.class},
                {SUPLA_REGISTER_DEVICE_C, SuplaRegisterDeviceCDecoderImpl.class},
                {SUPLA_REGISTER_DEVICE, SuplaRegisterDeviceDecoderImpl.class},

                // sc 
                {SUPLA_CHANNEL, SuplaChannelDecoderImpl.class},
                {SUPLA_CHANNEL_PACK, SuplaChannelPackDecoderImpl.class},
                {SUPLA_CHANNEL_VALUE,
                        pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelValueDecoderImpl.class},
                {SUPLA_EVENT, SuplaEventDecoderImpl.class},
                {SUPLA_LOCATION, SuplaLocationDecoderImpl.class},
                {SUPLA_LOCATION_PACK, SuplaLocationPackDecoderImpl.class},
                {SUPLA_REGISTER_CLIENT_RESULT, SuplaRegisterClientResultDecoderImpl.class},

                // sd
                {FIRMWARE_UPDATE_URL, FirmwareUpdateUrlDecoderImpl.class},
                {FIRMWARE_UPDATE_URL_RESULT, FirmwareUpdateUrlResultDecoderImpl.class},
                {SUPLA_CHANNEL_NEW_VALUE1,
                        pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaChannelNewValueDecoderImpl.class},
                {SUPLA_REGISTER_DEVICE_RESULT, SuplaRegisterDeviceResultDecoderImpl.class},

                // sdc 
                {SUPLA_GET_VERSION_RESULT, SuplaGetVersionResultDecoderImpl.class},
                {SUPLA_PING_SERVER_RESULT_CLIENT, SuplaPingServerResultClientDecoderImpl.class},
                {SUPLA_SET_ACTIVITY_TIMEOUT_RESULT, SuplaSetActivityTimeoutResultDecoderImpl.class},
                {SUPLA_VERSION_ERROR, SuplaVersionErrorDecoderImpl.class},

                // common 
                {SUPLA_CHANNEL_VALUE1, SuplaChannelValueDecoderImpl.class},
                {SUPLA_DATA_PACKET, SuplaDataPacketDecoderImpl.class},
                {TIMEVAL, TimevalDecoderImpl.class}
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
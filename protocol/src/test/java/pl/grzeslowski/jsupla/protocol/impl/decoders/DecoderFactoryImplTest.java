package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
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
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientDecoderImpl;
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
                {new SuplaChannelNewValueB(newInt(), new byte[SUPLA_CHANNELVALUE_SIZE]), SuplaChannelNewValueBDecoderImpl.class},
                {new SuplaChannelNewValue(newByte(), new byte[SUPLA_CHANNELVALUE_SIZE]), SuplaChannelNewValueDecoderImpl.class},
                {new SuplaRegisterClientB(newInt(), new byte[SUPLA_ACCESSID_PWD_MAXSIZE], new byte[SUPLA_GUID_SIZE], new byte[SUPLA_CLIENT_NAME_MAXSIZE], new byte[SUPLA_SOFTVER_MAXSIZE], new byte[SUPLA_SERVER_NAME_MAXSIZE]), SuplaRegisterClientBDecoderImpl.class},
                {new SuplaRegisterClient(newInt(), new byte[SUPLA_ACCESSID_PWD_MAXSIZE], new byte[SUPLA_GUID_SIZE], new byte[SUPLA_CLIENT_NAME_MAXSIZE], new byte[SUPLA_SOFTVER_MAXSIZE]), SuplaRegisterClientDecoderImpl.class},

                // dcs                                   
                {new SuplaSetActivityTimeout(newUnsignedByte()), SuplaSetActivityTimeoutDecoderImpl.class},

                // ds                                   
                {new FirmwareUpdateParams(newByte(), newInt(), newInt(), newInt(), newInt()), FirmwareUpdateParamsDecoderImpl.class},
                {new SuplaChannelNewValueResult(newUnsignedByte(), newInt(), newByte()), SuplaChannelNewValueResultDecoderImpl.class},
                {new SuplaDeviceChannelB(newUnsignedByte(), newInt(), newInt(), newInt(), new byte[SUPLA_CHANNELVALUE_SIZE]), SuplaDeviceChannelBDecoderImpl.class},
                {new SuplaDeviceChannel(newUnsignedByte(), newInt(), new byte[SUPLA_CHANNELVALUE_SIZE]), SuplaDeviceChannelDecoderImpl.class},
                {new SuplaDeviceChannelValue(newUnsignedByte(), new byte[SUPLA_CHANNELVALUE_SIZE]), SuplaDeviceChannelValueDecoderImpl.class},
                {new SuplaRegisterDeviceB(newInt(), new byte[SUPLA_LOCATION_PWD_MAXSIZE], new byte[SUPLA_GUID_SIZE], new byte[SUPLA_DEVICE_NAME_MAXSIZE], new byte[SUPLA_SOFTVER_MAXSIZE], (short) 0, new SuplaDeviceChannelB[0]), SuplaRegisterDeviceBDecoderImpl.class},
                {new SuplaRegisterDeviceC(newInt(), new byte[SUPLA_LOCATION_PWD_MAXSIZE], new byte[SUPLA_GUID_SIZE], new byte[SUPLA_DEVICE_NAME_MAXSIZE], new byte[SUPLA_SOFTVER_MAXSIZE], new byte[SUPLA_SERVER_NAME_MAXSIZE], (short) 0, new SuplaDeviceChannelB[0]), SuplaRegisterDeviceCDecoderImpl.class},
                {new SuplaRegisterDevice(newInt(), new byte[SUPLA_LOCATION_PWD_MAXSIZE], new byte[SUPLA_GUID_SIZE], new byte[SUPLA_DEVICE_NAME_MAXSIZE], new byte[SUPLA_SOFTVER_MAXSIZE], (short) 0, new SuplaDeviceChannel[0]), SuplaRegisterDeviceDecoderImpl.class},

                // sc 
                {new SuplaChannel(newByte(), newInt(), newInt(), newInt(), newByte(), new SuplaChannelValue(new byte[SUPLA_CHANNELVALUE_SIZE], new byte[SUPLA_CHANNELVALUE_SIZE]), 0, new byte[0]), SuplaChannelDecoderImpl.class},
                {new SuplaChannelPack(0, newInt(), new SuplaChannel[0]), SuplaChannelPackDecoderImpl.class},
                {new pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue(newByte(), newInt(), newByte(), new SuplaChannelValue(new byte[SUPLA_CHANNELVALUE_SIZE], new byte[SUPLA_CHANNELVALUE_SIZE])), pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelValueDecoderImpl.class},
                {new SuplaEvent(newInt(), newInt(), newUnsignedInt(), newInt(), 0, new byte[0]), SuplaEventDecoderImpl.class},
                {new SuplaLocation(newByte(), newInt(), 0, new byte[0]), SuplaLocationDecoderImpl.class},
                {new SuplaLocationPack(0, newInt(), new SuplaLocation[0]), SuplaLocationPackDecoderImpl.class},
                {new SuplaRegisterClientResult(newInt(), newInt(), newInt(), newInt(), newUnsignedByte(), newUnsignedByte(), newUnsignedByte()), SuplaRegisterClientResultDecoderImpl.class},

                // sd
                {new FirmwareUpdateUrl(newByte(), new byte[SUPLA_URL_HOST_MAXSIZE], newInt(), new byte[SUPLA_URL_PATH_MAXSIZE]), FirmwareUpdateUrlDecoderImpl.class},
                {new FirmwareUpdateUrlResult(newByte(), new FirmwareUpdateUrl(newByte(), new byte[SUPLA_URL_HOST_MAXSIZE], newInt(), new byte[SUPLA_URL_PATH_MAXSIZE])), FirmwareUpdateUrlResultDecoderImpl.class},
                {new pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue(newInt(), newUnsignedByte(), newUnsignedInt(), new byte[SUPLA_CHANNELVALUE_SIZE]), pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaChannelNewValueDecoderImpl.class},
                {new SuplaRegisterDeviceResult(newInt(), newByte(), (byte) 100, (byte) 99), SuplaRegisterDeviceResultDecoderImpl.class},

                // sdc 
                {new SuplaGetVersionResult(newUnsignedByte(), newUnsignedByte(), new byte[SUPLA_SOFTVER_MAXSIZE]), SuplaGetVersionResultDecoderImpl.class},
                {new SuplaSetActivityTimeoutResult(newUnsignedByte(), newUnsignedByte(), newUnsignedByte()), SuplaSetActivityTimeoutResultDecoderImpl.class},
                {new SuplaVersionError(newUnsignedByte(), newUnsignedByte()), SuplaVersionErrorDecoderImpl.class},

                // common 
                {new SuplaChannelValue(new byte[SUPLA_CHANNELVALUE_SIZE], new byte[SUPLA_CHANNELVALUE_SIZE]), SuplaChannelValueDecoderImpl.class},
                {new SuplaDataPacket(newUnsignedByte(), newUnsignedInt(), newUnsignedInt(), 0, new byte[0]), SuplaDataPacketDecoderImpl.class}
        });
    }

    @Test
    public void shouldFindProperDecoderForProto() throws Exception {

        // when
        final Decoder<ProtoWithSize> decoder = decoderFactory.getDecoder(proto);

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
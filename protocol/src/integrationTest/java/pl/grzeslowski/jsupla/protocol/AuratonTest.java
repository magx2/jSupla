package pl.grzeslowski.jsupla.protocol;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceGDecoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelExtendedValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SetCaption;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SubdeviceDetails;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelE;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelExtendedValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceG;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class AuratonTest {
    private final CallTypeParser callTypeParser = CallTypeParser.INSTANCE;
    private final DecoderFactory decoderFactory = DecoderFactoryImpl.INSTANCE;
    private final EncoderFactory encoderFactory = EncoderFactoryImpl.INSTANCE;

    @Test
    public void shouldAcceptSuplaRegisterDeviceG() {
        // given
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 76, 584, new byte[]{
            // email 256
            113, 64, 113, 46, 112, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //32 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //64
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //96
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //128
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //160
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //192
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//224
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//256
            // auth key 16
            -46, 69, -125, 101, -22, 124, 100, 34, -43, 60, -123, 5, -8, -79, -99, 97, //16
            // guid 16
            79, -23, 80, 72, -104, -37, -101, 37, -120, 32, 47, 76, -45, -9, 65, -15, // 16 
            // name 201
            65, 85, 82, 65, 84, 79, 78, 32, 66, 111, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//32
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//64
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//96
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//128
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //160
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //192
            0, 0, 0, 0, 0, 0, 0, 0, 0, // 201 
            // soft ver
            50, 52, 46, 48, 57, 46, 48, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //21
            // server name 65
            49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 51, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //32
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//64
            0,//65
            // flags 4 
            -48, 58, 0, 0,
            // product id 4
            18, 0, 1, 0,
            // channels count 1
            0
        });

        // when
        byte[] data = packet.data;
        SuplaRegisterDeviceG entity = SuplaRegisterDeviceGDecoder.INSTANCE.decode(data);

        // then
        assertThat(entity.email).isEqualTo(new byte[]{
            113, 64, 113, 46, 112, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //32 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //64
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //96
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //128
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //160
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //192
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//224
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//256
        });
        assertThat(entity.authKey).isEqualTo(new byte[]{
            -46, 69, -125, 101, -22, 124, 100, 34, -43, 60, -123, 5, -8, -79, -99, 97, //16
        });
        assertThat(entity.guid).isEqualTo(new byte[]{79, -23, 80, 72, -104, -37, -101, 37, -120, 32, 47, 76, -45, -9, 65, -15, // 16 
        });

        assertThat(entity.name).isEqualTo(new byte[]{65, 85, 82, 65, 84, 79, 78, 32, 66, 111, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//32
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//64
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//96
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//128
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //160
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //192
            0, 0, 0, 0, 0, 0, 0, 0, 0,});
        assertThat(entity.softVer).isEqualTo(new byte[]{50, 52, 46, 48, 57, 46, 48, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,});
        assertThat(entity.serverName).isEqualTo(new byte[]{49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 51, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //32
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//64
            0,});

        assertThat(entity.flags).isEqualTo(15056);
        assertThat(entity.manufacturerId).isEqualTo((short) 18);
        assertThat(entity.productId).isEqualTo((short) 1);
        assertThat(entity.channelCount).isZero();
    }

    @SuppressWarnings("UnusedAssignment")
    @Test
    public void shouldAcceptSuplaRegisterDeviceG1() {
        // given
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 76, 980, new byte[]{
            113, 64, 113, 46, 112, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            -46, 69, -125, 101, -22, 124, 100, 34, -43, 60, -123, 5, -8, -79, -99, 97,
            79, -23, 80, 72, -104, -37, -101, 37, -120, 32, 47, 76, -45, -9, 65, -15,
            65, 85, 82, 65, 84, 79, 78, 32, 66, 111, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            50, 52, 46, 48, 57, 46, 48, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 51, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0,
            -48, 58, 0, 0,
            18, 0, 1, 0,
            11,
            0, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, 1, 0, 0, 1, 1, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, 1, 0, 0, 1, 2, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, 1, 0, 0, 1, 3, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, 1, 0, 0, 1, 4, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, 1, 0, 0, 1, 5, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, 1, 0, 0, 1, 6, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, 1, 0, 0, 1, 7, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, 1, 0, 0, 1, 8, -38, 11, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 9, 84, 11, 0, 0, 0, 0, 0, 4, -64, 3, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 10, 84, 11, 0, 0, 0, 0, 0, 8, -54, 3, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        });

        // when
        byte[] data = packet.data;
        SuplaRegisterDeviceG entity = SuplaRegisterDeviceGDecoder.INSTANCE.decode(data);

        // then
        assertThat(entity.email).isEqualTo(new byte[]{
            113, 64, 113, 46, 112, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //32 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //64
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //96
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //128
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //160
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //192
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//224
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//256
        });
        assertThat(entity.authKey).isEqualTo(new byte[]{
            -46, 69, -125, 101, -22, 124, 100, 34, -43, 60, -123, 5, -8, -79, -99, 97, //16
        });
        assertThat(entity.guid).isEqualTo(new byte[]{79, -23, 80, 72, -104, -37, -101, 37, -120, 32, 47, 76, -45, -9, 65, -15, // 16 
        });

        assertThat(entity.name).isEqualTo(new byte[]{65, 85, 82, 65, 84, 79, 78, 32, 66, 111, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//32
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//64
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//96
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//128
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //160
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //192
            0, 0, 0, 0, 0, 0, 0, 0, 0,});
        assertThat(entity.softVer).isEqualTo(new byte[]{50, 52, 46, 48, 57, 46, 48, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,});
        assertThat(entity.serverName).isEqualTo(new byte[]{49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 51, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //32
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,//64
            0,});

        assertThat(entity.flags).isEqualTo(15056);
        assertThat(entity.manufacturerId).isEqualTo((short) 18);
        assertThat(entity.productId).isEqualTo((short) 1);
        assertThat(entity.channelCount).isEqualTo((short) 11);
        assertThat(entity.channels.length).isEqualTo((short) 11);

        int channelIdx = 0;
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(6100);
            assertThat(channel.funcList).isEqualTo(131072);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(420);
            assertThat(channel.flags).isEqualTo(419495936);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isNull();
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isEqualTo(new HVACValue((short) 0, (short) 2, (short) 2100, (short) 0, 1));
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 0
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(6100);
            assertThat(channel.funcList).isEqualTo(131072);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(420);
            assertThat(channel.flags).isEqualTo(419495936);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isNull();
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isEqualTo(new HVACValue((short) 0, (short) 2, (short) 2100, (short) 0, 1));
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 1
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(6100);
            assertThat(channel.funcList).isEqualTo(131072);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(420);
            assertThat(channel.flags).isEqualTo(419495936);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isNull();
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isEqualTo(new HVACValue((short) 0, (short) 2, (short) 2100, (short) 0, 1));
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 2
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(6100);
            assertThat(channel.funcList).isEqualTo(131072);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(420);
            assertThat(channel.flags).isEqualTo(419495936);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isNull();
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isEqualTo(new HVACValue((short) 0, (short) 2, (short) 2100, (short) 0, 1));
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 3
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(6100);
            assertThat(channel.funcList).isEqualTo(131072);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(420);
            assertThat(channel.flags).isEqualTo(419495936);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isNull();
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isEqualTo(new HVACValue((short) 0, (short) 2, (short) 2100, (short) 0, 1));
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 4
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(6100);
            assertThat(channel.funcList).isEqualTo(131072);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(420);
            assertThat(channel.flags).isEqualTo(419495936);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isNull();
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isEqualTo(new HVACValue((short) 0, (short) 2, (short) 2100, (short) 0, 1));
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 5
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(6100);
            assertThat(channel.funcList).isEqualTo(131072);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(420);
            assertThat(channel.flags).isEqualTo(419495936);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isNull();
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isEqualTo(new HVACValue((short) 0, (short) 2, (short) 2100, (short) 0, 1));
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 6
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(6100);
            assertThat(channel.funcList).isEqualTo(131072);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(420);
            assertThat(channel.flags).isEqualTo(419495936);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isNull();
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isEqualTo(new HVACValue((short) 0, (short) 2, (short) 2100, (short) 0, 1));
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 7
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(3034);
            assertThat(channel.funcList).isEqualTo(0);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(40);
            assertThat(channel.flags).isEqualTo(134283264);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isEqualTo(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isNull();
            assertThat(channel.defaultIcon).isEqualTo((short) 3);
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 8
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(2900);
            assertThat(channel.funcList).isEqualTo(67108864);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(960);
            assertThat(channel.flags).isEqualTo(134283264);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isEqualTo(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isNull();
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 9
        {
            SuplaDeviceChannelE channel = entity.channels[channelIdx];
            assertThat(channel.number).isEqualTo((short) channelIdx);
            assertThat(channel.type).isEqualTo(2900);
            assertThat(channel.funcList).isEqualTo(134217728);
            assertThat(channel.actionTriggerCaps).isNull();
            assertThat(channel.defaultValue).isEqualTo(970);
            assertThat(channel.flags).isEqualTo(134283264);
            assertThat(channel.offline).isZero();
            assertThat(channel.valueValidityTimeSec).isZero();
            assertThat(channel.value).isEqualTo(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
            assertThat(channel.actionTriggerProperties).isNull();
            assertThat(channel.hvacValue).isNull();
            assertThat(channel.defaultIcon).isZero();
            assertThat(channel.subDeviceId).isOne();
            channelIdx++;
        } // channel 10
        assertThat(channelIdx).isEqualTo(11);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void shouldParseSubDeviceDetails() {
        // given
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 1260, 325, new byte[]{
            1, 65, 85, 82, 65, 84, 79, 78, 32, 70, 108, 111, 111, 114, 32, 72, 101, 97, 116, 105, 110, 103, 32, 67, 111, 110, 116, 114, 111, 108, 108, 101,
            114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 46, 49, 51, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51,
            48, 49, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 48, 49, 48, 48, 51, 48, 70, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
        });

        // when
        CallType callType = callTypeParser.parse(packet.callId).get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        ProtoWithSize decode = decoder.decode(packet.data);

        // then
        assertThat(decode).isInstanceOf(SubdeviceDetails.class);
        SubdeviceDetails proto = (SubdeviceDetails) decode;

        assertThat(proto.subDeviceId).isEqualTo((short) 1);
        assertThat(proto.name).isEqualTo(new byte[]{65, 85, 82, 65, 84, 79, 78, 32, 70, 108, 111, 111, 114, 32, 72, 101,
            97, 116, 105, 110, 103, 32, 67, 111, 110, 116, 114, 111, 108, 108, 101, 114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        assertThat(proto.softVer).isEqualTo(new byte[]{50, 46, 49, 51, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        assertThat(proto.productCode).isEqualTo(new byte[]{51, 48, 49, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        assertThat(proto.serialNumber).isEqualTo(new byte[]{51, 48, 49, 48, 48, 51, 48, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void shouldParseCaption() {
        // given
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 640, 33, new byte[]{
            10, 0, 0, 0, //id
            25, 0, 0, 0,// caption size 
            70, 108, 111, 111, 114, 32, 72, 101, 97, 116, 105, 110, 103, 32, 67, 111, 110, 116, 114, 111, 108, 108, 101, 114, 0
        });

        // when
        CallType callType = callTypeParser.parse(packet.callId).get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        ProtoWithSize decode = decoder.decode(packet.data);

        // then
        assertThat(decode).isInstanceOf(SetCaption.class);
        SetCaption proto = (SetCaption) decode;
        assertThat(proto.channelNumber).isNull();
        assertThat(proto.id).isEqualTo(10);
        assertThat(proto.captionSize).isEqualTo(25);
        assertThat(proto.caption).isEqualTo(new byte[]{70, 108, 111, 111, 114, 32, 72, 101, 97, 116, 105, 110, 103, 32,
            67, 111, 110, 116, 114, 111, 108, 108, 101, 114, 0});
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void shouldParseExtendedValue() {
        // given
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 105, 227, new byte[]{
            10, 50, -35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        });

        // when
        CallType callType = callTypeParser.parse(packet.callId).get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        ProtoWithSize decode = decoder.decode(packet.data);

        // then
        assertThat(decode).isInstanceOf(SuplaDeviceChannelExtendedValue.class);
        SuplaDeviceChannelExtendedValue proto = (SuplaDeviceChannelExtendedValue) decode;
        assertThat(proto.channelNumber).isEqualTo((short) 10);
        SuplaChannelExtendedValue value = proto.value;
        assertThat(value.type).isEqualTo((byte) 50);
        assertThat(value.size).isEqualTo(221);
        assertThat(value.value).isEqualTo(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @SuppressWarnings("ConstantValue")
    @Test
    public void shouldParseFlags() {
        // given
        int flags = 15056;

        assertThat(flags & SUPLA_DEVICE_FLAG_CALCFG_ENTER_CFG_MODE).isNotZero();
        assertThat(flags & SUPLA_DEVICE_FLAG_SLEEP_MODE_ENABLED).isZero();
        assertThat(flags & SUPLA_DEVICE_FLAG_CALCFG_SET_TIME).isNotZero();
        assertThat(flags & SUPLA_DEVICE_FLAG_DEVICE_CONFIG_SUPPORTED).isNotZero();
        assertThat(flags & SUPLA_DEVICE_FLAG_DEVICE_LOCKED).isZero();
        assertThat(flags & SUPLA_DEVICE_FLAG_CALCFG_SUBDEVICE_PAIRING).isNotZero();
        assertThat(flags & SUPLA_DEVICE_FLAG_CALCFG_IDENTIFY_DEVICE).isZero();
        assertThat(flags & SUPLA_DEVICE_FLAG_CALCFG_RESTART_DEVICE).isNotZero();
        assertThat(flags & SUPLA_DEVICE_FLAG_ALWAYS_ALLOW_CHANNEL_DELETION).isNotZero();
    }
}

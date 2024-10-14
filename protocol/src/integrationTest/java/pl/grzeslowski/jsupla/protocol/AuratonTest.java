package pl.grzeslowski.jsupla.protocol;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceGDecoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SubdeviceDetails;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceG;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import java.util.Optional;

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
    }

    @Test
    public void shouldAcceptSuplaRegisterDeviceG2() {
        // given
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 76, 980, new byte[]{
            113, 64, 113, 46, 112, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            -46, 69, -125, 101, -22, 124, 100, 34, -43, 60, -123, 5, -8, -79, -99, 97,
            79, -23, 80, 72, -104, -37, -101, 37, -120, 32, 47, 76, -45, -9, 65, -15,
            65, 85, 82, 65, 84, 79, 78, 32, 66, 111, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            50, 52, 46, 48, 57, 46, 48, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 51, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            -48, 58, 0, 0,
            18, 0,
            1, 0,
            11,
            0, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 1, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 2, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 3, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 4, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 5, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 6, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 7, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 8, -38, 11, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 113, -64, 3, 1, 9, 84, 11, 0, 0, 0, 0, 0, 4, -64, 3, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 10, 84, 11, 0, 0, 0, 0, 0, 8, -54, 3, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        });

        // when
        byte[] data = packet.data;
        SuplaRegisterDeviceG entity = SuplaRegisterDeviceGDecoder.INSTANCE.decode(data);
    }

    @Test
    public void shouldAcceptSuplaRegisterDeviceG3() {
        // given
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 76, 980, new byte[]{
            113, 64, 113, 46, 112, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 69, -125, 101, -22, 124, 100, 34, -43, 60, -123, 5, -8, -79, -99, 97, 79, -23, 80, 72, -104, -37, -101, 37, -120, 32, 47, 76, -45, -9, 65, -15, 65, 85, 82, 65, 84, 79, 78, 32, 66, 111, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 52, 46, 48, 57, 46, 48, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 51, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -48, 58, 0, 0, 18, 0, 1, 0, 11, 0, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 1, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 2, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 3, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 4, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 5, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 6, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 7, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 8, -38, 11, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 113, -64, 3, 1, 9, 84, 11, 0, 0, 0, 0, 0, 4, -64, 3, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 10, 84, 11, 0, 0, 0, 0, 0, 8, -54, 3, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
        });

        // when
        byte[] data = packet.data;
        SuplaRegisterDeviceG entity = SuplaRegisterDeviceGDecoder.INSTANCE.decode(data);
        System.out.println(entity);
        System.out.println(entity.flags);

        System.out.println("email=" + ProtocolHelpers.parseString(entity.email));
        System.out.println("authKey=" + ProtocolHelpers.parseString(entity.authKey));
        System.out.println("guid=" + ProtocolHelpers.parseHexString(entity.guid));
        System.out.println("name=" + ProtocolHelpers.parseString(entity.name));
        System.out.println("softVer=" + ProtocolHelpers.parseString(entity.softVer));
        System.out.println("serverName=" + ProtocolHelpers.parseString(entity.serverName));
        System.out.println("flags=" + entity.flags);
        System.out.println("manufacturerId=" + entity.manufacturerId);
        System.out.println("productId=" + entity.productId);
        System.out.println("channelCount=" + entity.channelCount);
    }

    @Test
    public void SUPLA_DS_CALL_SET_SUBDEVICE_DETAILS() {
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
        Optional<CallType> callTypeOptional = callTypeParser.parse(packet.callId);
        if (!callTypeOptional.isPresent()) {
            return;
        }
        CallType callType = callTypeOptional.get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        SubdeviceDetails proto = (SubdeviceDetails) decoder.decode(packet.data);
        System.out.println(proto);
        System.out.println("name=" + ProtocolHelpers.parseString(proto.name));
        System.out.println("productCode=" + ProtocolHelpers.parseString(proto.productCode));
        System.out.println("serialNumber=" + ProtocolHelpers.parseString(proto.serialNumber));
    }

    @Test
    public void SUPLA_DCS_CALL_SET_CHANNEL_CAPTION_ID() {
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 640, 33, new byte[]{
            10, 0, 0, 0, //id
            25, 0, 0, 0,// caption size 
            70, 108, 111, 111, 114, 32, 72, 101, 97, 116, 105, 110, 103, 32, 67, 111, 110, 116, 114, 111, 108, 108, 101, 114, 0
        });
        Optional<CallType> callTypeOptional = callTypeParser.parse(packet.callId);
        if (!callTypeOptional.isPresent()) {
            return;
        }
        CallType callType = callTypeOptional.get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        ProtoWithSize proto = decoder.decode(packet.data);
        System.out.println(proto);
    }

    @Test
    public void SUPLA_DS_CALL_DEVICE_CHANNEL_EXTENDEDVALUE_CHANGED() {
        SuplaDataPacket packet = new SuplaDataPacket((short) 25, 1, 105, 227, new byte[]{
            10, 50, -35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        });
        Optional<CallType> callTypeOptional = callTypeParser.parse(packet.callId);
        if (!callTypeOptional.isPresent()) {
            return;
        }
        CallType callType = callTypeOptional.get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        ProtoWithSize proto = decoder.decode(packet.data);
        System.out.println(proto);
    }

    @Test
    public void flags() {
        // given
        int flags = 0;

        // Check for each flag
        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_ENTER_CFG_MODE) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_CALCFG_ENTER_CFG_MODE is set.");
        }

        if ((flags & SUPLA_DEVICE_FLAG_SLEEP_MODE_ENABLED) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_SLEEP_MODE_ENABLED is set.");
        }

        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_SET_TIME) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_CALCFG_SET_TIME is set.");
        }

        if ((flags & SUPLA_DEVICE_FLAG_DEVICE_CONFIG_SUPPORTED) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_DEVICE_CONFIG_SUPPORTED is set.");
        }

        if ((flags & SUPLA_DEVICE_FLAG_DEVICE_LOCKED) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_DEVICE_LOCKED is set.");
        }

        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_SUBDEVICE_PAIRING) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_CALCFG_SUBDEVICE_PAIRING is set.");
        }

        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_IDENTIFY_DEVICE) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_CALCFG_IDENTIFY_DEVICE is set.");
        }

        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_RESTART_DEVICE) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_CALCFG_RESTART_DEVICE is set.");
        }

        if ((flags & SUPLA_DEVICE_FLAG_ALWAYS_ALLOW_CHANNEL_DELETION) != 0) {
            System.out.println("SUPLA_DEVICE_FLAG_ALWAYS_ALLOW_CHANNEL_DELETION is set.");
        }

    }

    @Test
    public void register() {
        byte[] data = new byte[]{
            113, 64, 113, 46, 99, 111, 109, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 69, -125, 101, -22, 124, 100, 34, -43, 60, -123, 5, -8, -79, -99, 97, 79, -23, 80, 72, -104, -37, -101, 37, -120, 32, 47, 76, -45, -9, 65, -15, 65, 85, 82, 65, 84, 79, 78, 32, 66, 111, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 52, 46, 48, 57, 46, 48, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 51, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -48, 58, 0, 0, 18, 0, 1, 0, 13, 0, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 18, 7, 0, 0, 1, 0, 0, 1, 1, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 18, 7, 0, 0, 1, 0, 0, 1, 2, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 18, 7, 0, 0, 1, 0, 0, 1, 3, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 18, 7, 0, 0, 1, 0, 0, 1, 4, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 5, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 6, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 7, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 52, 8, 0, 0, -127, 0, 0, 1, 8, -38, 11, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 113, -64, 3, 1, 9, 84, 11, 0, 0, 0, 0, 0, 4, -64, 3, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 10, 84, 11, 0, 0, 0, 0, 0, 8, -54, 3, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 11, -44, 23, 0, 0, 0, 0, 2, 0, -92, 1, 0, 0, 0, 0, 1, 25, 0, 0, 0, 0, 0, -124, 3, 0, 0, 0, 2, 18, 7, 0, 0, 1, 0, 0, 2, 12, -38, 11, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 0, -124, 3, 0, 0, -51, -52, -52, -52, -52, -52, 55, 64, 7, 2
        };

        SuplaRegisterDeviceG entity = SuplaRegisterDeviceGDecoder.INSTANCE.decode(data);
        print(entity);
    }

    private void print(SuplaRegisterDeviceG entity) {
        System.out.println(entity);

        System.out.println("email=" + ProtocolHelpers.parseString(entity.email));
        System.out.println("authKey=" + ProtocolHelpers.parseString(entity.authKey));
        System.out.println("guid=" + ProtocolHelpers.parseHexString(entity.guid));
        System.out.println("name=" + ProtocolHelpers.parseString(entity.name));
        System.out.println("softVer=" + ProtocolHelpers.parseString(entity.softVer));
        System.out.println("serverName=" + ProtocolHelpers.parseString(entity.serverName));
        int flags = entity.flags;
        System.out.println("flags=" + flags);
        // Check for each flag
        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_ENTER_CFG_MODE) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_CALCFG_ENTER_CFG_MODE is set.");
        }
        if ((flags & SUPLA_DEVICE_FLAG_SLEEP_MODE_ENABLED) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_SLEEP_MODE_ENABLED is set.");
        }
        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_SET_TIME) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_CALCFG_SET_TIME is set.");
        }
        if ((flags & SUPLA_DEVICE_FLAG_DEVICE_CONFIG_SUPPORTED) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_DEVICE_CONFIG_SUPPORTED is set.");
        }
        if ((flags & SUPLA_DEVICE_FLAG_DEVICE_LOCKED) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_DEVICE_LOCKED is set.");
        }
        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_SUBDEVICE_PAIRING) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_CALCFG_SUBDEVICE_PAIRING is set.");
        }
        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_IDENTIFY_DEVICE) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_CALCFG_IDENTIFY_DEVICE is set.");
        }
        if ((flags & SUPLA_DEVICE_FLAG_CALCFG_RESTART_DEVICE) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_CALCFG_RESTART_DEVICE is set.");
        }
        if ((flags & SUPLA_DEVICE_FLAG_ALWAYS_ALLOW_CHANNEL_DELETION) != 0) {
            System.out.println("\tSUPLA_DEVICE_FLAG_ALWAYS_ALLOW_CHANNEL_DELETION is set.");
        }
        System.out.println("manufacturerId=" + entity.manufacturerId);
        System.out.println("productId=" + entity.productId);
        System.out.println("channelCount=" + entity.channelCount);
    }


}

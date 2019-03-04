package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_AUTHKEY_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_EMAIL_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaRegisterDeviceDRandomizer implements Randomizer<SuplaRegisterDeviceD> {
	private final RandomSupla randomSupla;

	public SuplaRegisterDeviceDRandomizer(final RandomSupla randomSupla) {
		this.randomSupla = randomSupla;
	}

	@Override
	public SuplaRegisterDeviceD getRandomValue() {
		final short channelCount = randomSupla.nextUnsignedByte((short) SUPLA_CHANNELMAXCOUNT);
		final SuplaDeviceChannelB[] channels = randomSupla.objects(SuplaDeviceChannelB.class, channelCount)
				.toArray(SuplaDeviceChannelB[]::new);
		return new SuplaRegisterDeviceD(
				randomSupla.nextByteArray(SUPLA_EMAIL_MAXSIZE),
				randomSupla.nextByteArray(SUPLA_AUTHKEY_SIZE),
				randomSupla.nextByteArray(SUPLA_GUID_SIZE),
				randomSupla.nextByteArray(SUPLA_DEVICE_NAME_MAXSIZE),
				randomSupla.nextByteArray(SUPLA_SOFTVER_MAXSIZE),
				randomSupla.nextByteArray(SUPLA_SERVER_NAME_MAXSIZE),
				channelCount,
				channels
		);
	}
}

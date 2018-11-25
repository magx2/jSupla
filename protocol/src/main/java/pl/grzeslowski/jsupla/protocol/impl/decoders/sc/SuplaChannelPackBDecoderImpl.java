package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelPackBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

public final class SuplaChannelPackBDecoderImpl implements SuplaChannelPackBDecoder {
	private final SuplaChannelBDecoder suplaChannelBDecoder;

	public SuplaChannelPackBDecoderImpl(SuplaChannelBDecoder suplaChannelBDecoder) {
		this.suplaChannelBDecoder = requireNonNull(suplaChannelBDecoder);
	}

	@Override
	public SuplaChannelPackB decode(byte[] bytes, int offset) {
		final int count = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
		offset += INT_SIZE;
		final int totalLeft = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
		offset += INT_SIZE;
		final SuplaChannelB[] channels = new SuplaChannelB[count];
		for (int i = 0; i < count; i++) {
			if (bytes.length - offset < SuplaChannelB.MIN_SIZE) {
				throw new IllegalArgumentException(format(
						"Can't parse SuplaChannelB from byte array of length %s with offset %s, " +
								"because min length is %s!", bytes.length, offset, SuplaChannelB.MIN_SIZE));
			}
			final SuplaChannelB channel = suplaChannelBDecoder.decode(bytes, offset);
			offset += channel.size();
			channels[i] = channel;
		}
		return new SuplaChannelPackB(count, totalLeft, channels);
	}
}

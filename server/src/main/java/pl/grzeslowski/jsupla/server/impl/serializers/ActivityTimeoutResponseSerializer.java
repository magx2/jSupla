package pl.grzeslowski.jsupla.server.impl.serializers;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.server.api.entities.responses.ActivityTimeoutResponse;
import pl.grzeslowski.jsupla.server.api.serializers.Serializer;

public class ActivityTimeoutResponseSerializer
        implements Serializer<ActivityTimeoutResponse, SuplaSetActivityTimeoutResult> {
    @Override
    public SuplaSetActivityTimeoutResult serialize(ActivityTimeoutResponse response) {
        short activityTimeout = (short) response.getActivityTimeout();
        short min = (short) response.getMin();
        short max = (short) response.getMax();

        return new SuplaSetActivityTimeoutResult(activityTimeout, min, max);
    }
}

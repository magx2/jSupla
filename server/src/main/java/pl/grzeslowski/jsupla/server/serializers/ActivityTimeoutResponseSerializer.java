package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.server.entities.responses.ActivityTimeoutResponse;

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

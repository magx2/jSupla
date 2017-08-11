package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.server.entities.requests.ActivityTimeoutRequest;

public class ActivityTimeoutRequestParser implements Parser<ActivityTimeoutRequest, SuplaSetActivityTimeout> {
    @Override
    public ActivityTimeoutRequest parse(SuplaSetActivityTimeout proto) {
        return new ActivityTimeoutRequest(proto.activityTimeout);
    }
}

package pl.grzeslowski.jsupla.server.impl.parsers;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.server.api.entities.requests.ds.ActivityTimeoutRequest;
import pl.grzeslowski.jsupla.server.api.parsers.Parser;

public class ActivityTimeoutRequestParser implements Parser<ActivityTimeoutRequest, SuplaSetActivityTimeout> {
    @Override
    public ActivityTimeoutRequest parse(SuplaSetActivityTimeout proto) {
        return new ActivityTimeoutRequest(proto.activityTimeout);
    }
}

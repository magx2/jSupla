package pl.grzeslowski.jsupla.server.logic;

import pl.grzeslowski.jsupla.server.entities.ActivityTimeoutRequest;
import pl.grzeslowski.jsupla.server.entities.ActivityTimeoutResponse;

public interface ActivityTimeoutListener {
    ActivityTimeoutResponse onActivityTimeoutChange(ActivityTimeoutRequest request);
}

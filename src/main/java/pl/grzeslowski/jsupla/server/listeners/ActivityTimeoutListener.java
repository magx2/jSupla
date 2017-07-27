package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.ActivityTimeoutRequest;
import pl.grzeslowski.jsupla.server.entities.responses.ActivityTimeoutResponse;

public interface ActivityTimeoutListener {
    ActivityTimeoutResponse onActivityTimeoutChange(ActivityTimeoutRequest request);
}

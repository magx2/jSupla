package pl.grzeslowski.jsupla.server.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static pl.grzeslowski.jsupla.Preconditions.size;

public class ActivityTimeoutRequest implements Entity {
    @Min(0)
    @Max(255)
    private final int activityTimeout;

    public ActivityTimeoutRequest(int activityTimeout) {
        this.activityTimeout = size(activityTimeout, 0, 255);
    }

    public int getActivityTimeout() {
        return activityTimeout;
    }

    @Override
    public String toString() {
        return "ActivityTimeoutRequest{" +
                "activityTimeout=" + activityTimeout +
                '}';
    }
}

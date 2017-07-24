package pl.grzeslowski.jsupla.server.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static pl.grzeslowski.jsupla.Preconditions.size;

public class ActivityTimeoutResponse implements Entity {
    @Min(0)
    @Max(255)
    private final int activityTimeout;
    @Min(0)
    @Max(255)
    private final int min;
    @Min(0)
    @Max(255)
    private final int max;

    public ActivityTimeoutResponse(int activityTimeout, int min, int max) {
        this.activityTimeout = size(activityTimeout, 0, 255);
        this.min = size(min, 0, 255);
        this.max = size(max, 0, 255);
    }

    public int getActivityTimeout() {
        return activityTimeout;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "ActivityTimeoutResponse{" +
                "activityTimeout=" + activityTimeout +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}

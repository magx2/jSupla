package pl.grzeslowski.jsupla.server.api;

public class NotStartedException extends RuntimeException {
    private static final long serialUID = 1L;

    public NotStartedException() {
        super("Server is not started! Please call run() method before subscribing.");
    }

    public NotStartedException(final String message) {
        super(message);
    }
}

package pl.grzeslowski.jsupla.proto;

public final class TSD_FirmwareUpdate_UrlResult {
    public final byte exists;
    public final TSD_FirmwareUpdate_Url url;

    public TSD_FirmwareUpdate_UrlResult(byte exists, TSD_FirmwareUpdate_Url url) {
        this.exists = exists;
        this.url = url;
    }

    @Override
    public String toString() {
        return "TSD_FirmwareUpdate_UrlResult{" +
                "exists=" + exists +
                ", url=" + url +
                '}';
    }
}

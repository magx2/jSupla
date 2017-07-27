package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.consts.CallType;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;

public final class TSD_FirmwareUpdate_UrlResult implements ServerDevice {
    public final byte exists;
    public final TSD_FirmwareUpdate_Url url;

    public TSD_FirmwareUpdate_UrlResult(byte exists, TSD_FirmwareUpdate_Url url) {
        this.exists = exists;
        this.url = url;
    }


    @Override
    public CallType callType() {
        throw new UnsupportedOperationException();
    }
    @Override
    public int size() {
        assert url != null;
        return BYTE_SIZE + url.size();
    }

    @Override
    public String toString() {
        return "TSD_FirmwareUpdate_UrlResult{" +
                "exists=" + exists +
                ", url=" + url +
                '}';
    }
}
package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;

public final class TSD_FirmwareUpdate_UrlResult  implements Proto {
    public final byte exists;
    public final TSD_FirmwareUpdate_Url url;

    public TSD_FirmwareUpdate_UrlResult(byte exists, TSD_FirmwareUpdate_Url url) {
        this.exists = exists;
        this.url = url;
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
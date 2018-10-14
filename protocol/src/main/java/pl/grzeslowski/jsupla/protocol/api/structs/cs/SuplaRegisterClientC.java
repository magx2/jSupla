package pl.grzeslowski.jsupla.protocol.api.structs.cs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

/**
 * @since ver. 7
 */
public final class SuplaRegisterClientC implements ClientServer {
    /**
     * UTF-8.
     */
    public final byte[] email;
    public final byte[] authKey;
    public final byte[] guid;
    /**
     * UTF-8.
     */
    public final byte[] name;
    public final byte[] softVer;
    public final byte[] serverName;

    public SuplaRegisterClientC(byte[] email, byte[] authKey, byte[] guid, byte[] name, byte[] softVer, byte[] serverName) {
        this.email = email;
        this.authKey = authKey;
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_CLIENT_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = checkArrayLength(serverName, SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    public ClientServerCallType callType() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}

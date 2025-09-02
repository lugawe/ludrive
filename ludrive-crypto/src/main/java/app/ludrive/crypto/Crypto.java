package app.ludrive.crypto;

import app.ludrive.core.exception.CryptoException;

import com.google.crypto.tink.config.TinkConfig;

public final class Crypto {

    static {
        try {
            TinkConfig.register();
        } catch (Exception e) {
            throw new CryptoException("cannot register tink config", e);
        }
    }

    private Crypto() {}
}

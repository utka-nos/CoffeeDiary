package org.example.config;

import com.nimbusds.jose.jwk.RSAKey;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Data
@Slf4j
public class JwkUtils {

    public static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKeyPair();
        RSAPublicKey  rsaPublicKey  = (RSAPublicKey)  keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(rsaPublicKey)
                .privateKey(rsaPrivateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    public static KeyPair generateRsaKeyPair() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            log.error("Error during generating rsa key pair. Error: {{}}", ex.getMessage());
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

}

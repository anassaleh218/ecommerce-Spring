package eshopping.demo.config;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

@Configuration
public class TripleDesEncryptor implements AttributeConverter<Object, String> {

    @Value("${3des.encryption.key}")
    private String encryptionKey;

    private final String encryptionCipher = "DESede";

    private Key key;
    private Cipher cipher;

    private Key getKey() {
        if (key == null)
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
        return key;
    }

    private Cipher getCipher() throws GeneralSecurityException {
        if (cipher == null)
            cipher = Cipher.getInstance(encryptionCipher);
        return cipher;
    }

    private void initCipher(int encryptMode) throws GeneralSecurityException {
        getCipher().init(encryptMode, getKey());
    }

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Object attribute) {
        if (attribute == null)
            return null;
        initCipher(Cipher.ENCRYPT_MODE);
        byte[] bytes = SerializationUtils.serialize(attribute);
        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
    }

    @SneakyThrows
    @Override
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        initCipher(Cipher.DECRYPT_MODE);
        byte[] decryptedBytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
        return deserialize(decryptedBytes);
    }

    @SneakyThrows
    private Object deserialize(byte[] bytes) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        }
    }
}

// --------------------------------------------------------------------

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.encrypt.TextEncryptor;
// import org.springframework.security.crypto.encrypt.Encryptors;
// import javax.persistence.AttributeConverter;

// @Configuration
// public class TripleDesEncryptor implements AttributeConverter<String, String> {

//     @Value("${3des.encryption.password}")
//     private String encryptionPassword;

//     @Value("${3des.encryption.salt}")
//     private String encryptionSalt;

//     @Override
//     public String convertToDatabaseColumn(String attribute) {
//         if (attribute == null)
//             return null;
//         TextEncryptor encryptor = Encryptors.delux(encryptionPassword, encryptionSalt);
//         return encryptor.encrypt(attribute);
//     }

//     @Override
//     public String convertToEntityAttribute(String dbData) {
//         if (dbData == null)
//             return null;
//         TextEncryptor decryptor = Encryptors.delux(encryptionPassword, encryptionSalt);
//         return decryptor.decrypt(dbData);
//     }
// }

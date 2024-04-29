package eshopping.demo.auth;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class Des {
    //////////
    byte[] secretKey = "9mng65v8jf4lxn93nabf981m".getBytes();
    SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");
    byte[] iv = "a76nb5h9".getBytes();
    IvParameterSpec ivSpec = new IvParameterSpec(iv);
    /////////

    public String encrypt(String request) throws Exception{
        Cipher encryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        byte[] email = request.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(email); 
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        return encodedMessage;
    }


    public String decrypt(String request) throws Exception{
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(request);
        Cipher decryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        return decryptedMessage;
    }
}

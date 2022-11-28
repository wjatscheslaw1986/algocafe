package algos.encryption;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EncryptionTests {

    @Test
    public void encDecTextTest() {
        String input = "Lorem ipsum hello world whatever else";
        KeyPair temp = EncryptionUtil.encrypt(input.getBytes(StandardCharsets.UTF_8));
        String encrypted = new String(temp.getKey2());
        Assertions.assertNotEquals(input, encrypted);
        System.out.println(encrypted);
        String decrypted = new String(EncryptionUtil.decrypt(temp));
        Assertions.assertEquals(input, decrypted);
        System.out.println(decrypted);
    }

    @Test
    public void encDecImageTest() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/coffee.png");
             FileOutputStream fos = new FileOutputStream("src/test/resources/coffeeEnc.png");
             FileInputStream fis2 = new FileInputStream("src/test/resources/coffeeEnc.png")) {
            byte[] originalBytes = fis.readAllBytes();
            KeyPair temp = EncryptionUtil.encrypt(originalBytes);
            Assertions.assertNotEquals(new String(originalBytes), new String(temp.getKey2()));
            fos.write(temp.getKey2());
            byte[] encryptedBytes = fis2.readAllBytes();
            Assertions.assertNotEquals(new String(originalBytes), new String(encryptedBytes));
            Assertions.assertEquals(new String(temp.getKey2()), new String(encryptedBytes));
            byte[] decrypted = EncryptionUtil.decrypt(new KeyPair(temp.getKey1(), encryptedBytes));
            Assertions.assertEquals(new String(originalBytes), new String(decrypted));
        } catch (IOException ioe) {
            System.err.println("File not found");
        }
    }
}

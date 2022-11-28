package algos.encryption;

import java.util.Random;

public class EncryptionUtil {

    public static KeyPair encrypt(byte[] input) {
        byte[] dummy = generateRandom(input.length);
        byte[] encryptedData = new byte[input.length];
        for (int i = 0; i < input.length; i++) encryptedData[i] = (byte) (input[i] ^ dummy[i]);
        return new KeyPair(dummy, encryptedData);
    }

    public static byte[] decrypt(KeyPair input) {
        byte[] result = new byte[input.getKey1().length];
        for (int i = 0; i < result.length; i++) result[i] = (byte) (input.getKey1()[i] ^ input.getKey2()[i]);
        return result;
    }

    private static byte[] generateRandom(int length) {
        byte[] result = new byte[length];
        new Random().nextBytes(result);
        return result;
    }

}

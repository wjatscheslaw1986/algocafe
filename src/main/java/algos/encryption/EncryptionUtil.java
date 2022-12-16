// MODIFIED BY VIACHESLAV MIKHAILOV
// From Classic Computer Science Problems in Java
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
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

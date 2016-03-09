package org.costa;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class App {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final List<String> ALGORITHMS = Arrays.asList("MD2", "MD5",
            "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512");
    private static final int FOUR = 4;
    private static final int HEX_0XF0 = 0xF0;
    private static final int HEX_0X0F = 0x0F;

    private App() {
    }

    public static void main(final String[] args)
        throws NoSuchAlgorithmException {
        if (args.length != 2
                || !ALGORITHMS.contains(args[0]
                    .toUpperCase(Locale.getDefault()))) {
            System.out.println("Usage: java -jar MD5Generator.jar <algorithm>"
                    + " <password>");
            System.out.println("Available algorithms: MD2 MD5 SHA-1 SHA-224"
                    + "SHA-256 SHA-384 SHA-512");
            return;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(args[0]);
        messageDigest.update(args[1].getBytes(StandardCharsets.UTF_8));
        byte[] result = messageDigest.digest();
        System.out.println(new String(encodeHex(result)));
    }

    private static char[] encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(HEX_0XF0 & data[i]) >>> FOUR];
            out[j++] = DIGITS_LOWER[HEX_0X0F & data[i]];
        }
        return out;
    }

}

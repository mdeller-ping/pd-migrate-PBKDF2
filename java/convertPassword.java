import java.math.BigInteger;
import java.util.Base64;

public class convertPassword {
    
    public static void main (String[] args) {

        String base64Salt;
        String encodedPassword;
        Integer rounds;

        // command line arguments

        if (args.length != 3) {
            System.err.println ("java convertPassword base64Salt encodedPassword rounds");
            System.exit(1);
        }

            base64Salt = args[0];
            encodedPassword = args[1];
            rounds = Integer.parseInt(args[2]);


        byte cipherVersion = 0x00; // SHA1

        byte[] iterations = BigInteger.valueOf(rounds.longValue()).toByteArray();

        // Decode salt and password
        byte[] saltBytes = Base64.getDecoder().decode(base64Salt);
        byte[] encodedPasswordBytes = Base64.getDecoder().decode(encodedPassword);

        // Salt needs to be between 8 and 127
        byte saltLength = (byte) saltBytes.length;

        // Stitch together full value
        // 1 byte cipher
        // 1 byte salt length
        // 'saltLength' bytes for the salt
        // 2 bytes for the iterations
        // Remainder of bytes are for the encoded value

        final byte[] fullEncodedValue =
                new byte[1 + 1 + saltLength + 2 + encodedPasswordBytes.length];

        fullEncodedValue[0] = cipherVersion;
        fullEncodedValue[1] = saltLength;
        System.arraycopy(saltBytes, 0, fullEncodedValue, 2, saltLength);
        System.arraycopy(iterations, 0, fullEncodedValue, 2 + saltLength, 2);
        System.arraycopy(encodedPasswordBytes, 0, fullEncodedValue, 2 + saltLength + 2,
                encodedPasswordBytes.length);

        // Encode and add prefix
        System.out.println ("{PBKDF2}" + new String(Base64.getEncoder().encode(fullEncodedValue)));;

    }


}

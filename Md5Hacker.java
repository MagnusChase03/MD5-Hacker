package MD5;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hacker {

    private Md5Hacker () {

        getPassword("7DE70DBD65AC26EE49505A94F32DD0DD",5);
        System.out.println(getHash("p1/!"));

    }

    private String getHash(String message) {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(message.getBytes());
            byte[] semiHash = md.digest();
            String hash = DatatypeConverter.printHexBinary(semiHash).toUpperCase();
            return hash;

        } catch (NoSuchAlgorithmException ignored) {}

        return "";

    }

    private void getPassword(String hash, int max) {

        String pass = " ";
        System.out.println("At length 1");
        while (pass.length() < max) {

            char[] characters = pass.toCharArray();
            for (int i = 32; i <= 126; i++) {

                characters[characters.length - 1] = (char) i;
                if (getHash(String.copyValueOf(characters)).equals(hash)) {

                    System.out.println("Password is " + String.copyValueOf(characters));
                    return;

                }

            }

            int tildaCount = 0;
            boolean newSet = false;
            for (char chara : characters) {

                if (chara == '~') {

                    tildaCount++;

                }

            }

            if (tildaCount == characters.length) {

                characters = new char[characters.length + 1];
                for (int i = 0; i < characters.length; i++) {

                    characters[i] = ' ';

                }
                System.out.println("At length " + characters.length);
                newSet = true;

            }

            if (!newSet) {

                int index = characters.length - 2;
                while (index >= 0 && characters[index] == '~') {

                    characters[index] = ' ';
                    index--;

                }

                if (index >= 0) {

                    characters[index] = (char) (characters[index] + 1);

                }

            }

            pass = String.copyValueOf(characters);

        }

    }

    public static void main(String[] args) {

        new Md5Hacker();

    }

}

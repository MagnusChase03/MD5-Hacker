package MD5;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Md5Hacker {

    private Md5Hacker () {

        UI();

    }

    private void UI() {

        Scanner scan = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("done")) {

            System.out.print("Hash or Crack or Done: ");
            input = scan.nextLine();

            if (input.equalsIgnoreCase("Hash")) {

                System.out.print("Hash: ");
                String hash = scan.nextLine();
                System.out.println(getHash(hash));

            } else if (input.equalsIgnoreCase("Crack")) {

                System.out.print("Hash: ");
                String hash = scan.nextLine();
                System.out.print("Max length: ");
                String max = scan.nextLine();
                getPassword(hash, Integer.parseInt(max));

            }

        }

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
        while (pass.length() <= max) {

            char[] characters = pass.toCharArray();

            if (updateLast(characters, hash)) {

                return;

            }

            int old = characters.length;
            characters = makeNew(characters);

            if (old == characters.length) {

                updateWhenDone(characters);

            }

            pass = String.copyValueOf(characters);

        }

    }

    private boolean updateLast(char[] characters, String hash) {

        for (int i = 32; i <= 126; i++) {

            characters[characters.length - 1] = (char) i;
            if (getHash(String.copyValueOf(characters)).equals(hash)) {

                System.out.println("Password is " + String.copyValueOf(characters));
                return true;

            }

        }

        return false;

    }

    private char[] makeNew(char[] characters) {

        int tildaCount = 0;
        for (char chara : characters) {

            if (chara == '~') {

                tildaCount++;

            }

        }

        if (tildaCount == characters.length) {

            characters = new char[characters.length + 1];
            Arrays.fill(characters, ' ');
            return characters;

        }

        return characters;

    }

    private void updateWhenDone(char[] characters) {

        int index = characters.length - 1;
        while (index >= 0 && characters[index] == '~') {

            characters[index] = ' ';
            index--;

        }

        if (index >= 0) {

            characters[index] = (char) (characters[index] + 1);

        }

    }

    public static void main(String[] args) {

        new Md5Hacker();

    }

}

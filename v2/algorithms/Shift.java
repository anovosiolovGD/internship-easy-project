package EncryptionProject.v2.algorithms;

public class Shift implements CipherStrategy {
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int key;
    private final String direction;

    public Shift(int key, String direction) {
        this.direction = direction;
        this.key = key;
    }
    @Override
    public String encryption(String text) {
        StringBuilder encryptString = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ' || c == '!') {
                encryptString.append(c);
            } else {
                char ch = direction.equals("enc") ? ALPHABET.charAt((ALPHABET.indexOf(c) + key) % 26) : ALPHABET.charAt((ALPHABET.indexOf(c) - key) % 26);
                encryptString.append(ch);
            }
        }
        return new String(encryptString).toLowerCase();
    }
}

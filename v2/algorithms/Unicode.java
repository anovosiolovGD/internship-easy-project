package EncryptionProject.v2.algorithms;

public class Unicode implements CipherStrategy {
    private final int shift;
    private final String direction;

    public Unicode(int shift, String direction) {
        this.direction = direction;
        this.shift = shift;
    }

    @Override
    public String encryption(String text) {
        StringBuilder encryptString = new StringBuilder();
        for (char c : text.toCharArray()) {
            char ch = (char) (direction.equals("enc") ? c + shift : c - shift);
            encryptString.append(ch);
        }
        return new String(encryptString);
    }
}

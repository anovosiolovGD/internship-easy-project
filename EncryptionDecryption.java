package EncryptionProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Hello
 * Before testing it please read readme.md
 */
public class EncryptionDecryption {
    public static void main(String[] args) throws RuntimeException {

        String alg = "";
        String dir = "enc";
        int shift = 0;
        String mesStr = "";
        String inPath = "";
        String outPath = "";

        for (int i = 0; i < args.length - 1; i++) {
            if ("-alg".equals(args[i])) {
                alg = args[i + 1];
            }
            if ("-mode".equals(args[i])) {
                dir = args[i + 1];
            }
            if ("-key".equals(args[i])) {
                shift = Integer.parseInt(args[i + 1]);
            }
            if ("-data".equals(args[i])) {
                mesStr = args[i + 1];
            }
            if ("-in".equals(args[i])) {
                inPath = args[i + 1];
            }
            if ("-out".equals(args[i])) {
                outPath = args[i + 1];
            }
        }
        try {
            File in = new File(inPath);
            if (!mesStr.isEmpty()) {
                System.out.println(encrypt(alg, mesStr, shift, dir));
            } else if (!inPath.isEmpty()) {
                String inText = new Scanner(in).nextLine();
                String encrText = encrypt(alg, inText, shift, dir);
                File out = new File(outPath);
                FileWriter pw = new FileWriter(out);
                assert encrText != null;
                pw.write(encrText);
                pw.close();
            }
        } catch (RuntimeException | IOException e) {
            System.out.println("Error");
        }
    }

    //------------------------------------------ ENC ---------------------------------->
    public static String encrypt(String alg, String mesStr, int shift, String dir) {
        char[] message = mesStr.toCharArray();
        StringBuilder encryptStr = new StringBuilder();
        switch (alg) {
            case "shift":
                String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                for (char c : message) {
                    if (c == ' ' || c == '!') {
                        encryptStr.append(c);
                    } else {
                        char ch = (dir.equals("enc") ? alpha.charAt((alpha.indexOf(c) - 26 + shift) % 26) : alpha.charAt((alpha.indexOf(c) + 26 - shift) % 26));
                        encryptStr.append(ch);
                    }
                }
                return new String(encryptStr).toLowerCase();

            case "unicode":
                for (char c : message) {
                    char ch = (char) (dir.equals("enc") ? c + shift : c - shift);
                    encryptStr.append(ch);
                }
                return new String(encryptStr);
        }
        return null;
    }
}


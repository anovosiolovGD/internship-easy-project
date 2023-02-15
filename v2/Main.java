package EncryptionProject.v2;


import EncryptionProject.v2.algorithms.CipherStrategy;
import EncryptionProject.v2.algorithms.Shift;
import EncryptionProject.v2.algorithms.Unicode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, String> configurations = new HashMap<>();
    private static CipherStrategy cipherStrategy;

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i += 2) {
            configurations.put(args[i], args[i + 1]);
        }

        final String algorithm = configurations.get("-alg");
        final String direction = configurations.get("-mode");
        final int key = Integer.parseInt(configurations.get("-key"));
        final File input = new File(configurations.get("-in"));
        final File output = new File(configurations.get("-out"));

        switch (algorithm) {
            case "shift" -> cipherStrategy = new Shift(key, direction);
            case "unicode" -> cipherStrategy = new Unicode(key, direction);
        }

        String text;
        if (configurations.containsKey("-data")) {
            text = configurations.get("-data");
            System.out.println(cipherStrategy.encryption(text));
        } else {
            try (final Scanner scan = new Scanner(input); final FileWriter writer = new FileWriter(output)) {
                text = scan.nextLine();
                writer.write(cipherStrategy.encryption(text));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}

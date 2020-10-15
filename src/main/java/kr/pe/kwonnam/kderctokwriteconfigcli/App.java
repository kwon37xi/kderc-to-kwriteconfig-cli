/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kr.pe.kwonnam.kderctokwriteconfigcli;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Requires KDERCfile.");
            System.exit(-1);
        }

        KdeRcToKwriteconfigCli kdeRcToKwriteconfigCli = new KdeRcToKwriteconfigCli(Paths.get(args[0]));
        try (BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out))) {
            kdeRcToKwriteconfigCli.convertToCli(bufferedWriter);
        }
    }
}
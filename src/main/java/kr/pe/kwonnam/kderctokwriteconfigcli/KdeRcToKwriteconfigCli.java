package kr.pe.kwonnam.kderctokwriteconfigcli;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class KdeRcToKwriteconfigCli {
    public static final String KWRITECONFIG_COMMAND = "kwriteconfig5";

    private final Path targetRcFile;
    private final String kwriteconfigCommand;

    public KdeRcToKwriteconfigCli(Path targetRcFile) {
        this(targetRcFile, KWRITECONFIG_COMMAND);
    }

    public KdeRcToKwriteconfigCli(Path targetRcFile, String kwriteconfigCommand) {
        this.targetRcFile = targetRcFile;
        this.kwriteconfigCommand = kwriteconfigCommand;
    }

    public void convertToCli(Writer commandsWriter) {
        String fileFullPath = targetRcFile.toAbsolutePath().toString();

        String currentGroup = null;
        List<String> lines = readLines();

        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);

            if (currentLine.startsWith("[")) {
                currentGroup = parseGroup(currentLine);
                continue;
            }

            if (currentLine.trim().isEmpty()) {
                continue;
            }

            String command = generateCommand(fileFullPath, currentGroup, currentLine);
            writeCommand(commandsWriter, command);
        }
    }

    private void writeCommand(Writer commandsWriter, String command) {
        try {
            commandsWriter.append(command).append(System.lineSeparator());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write command - " + command, e);
        }
    }

    private String parseGroup(String currentLine) {
        String currentGroup;
        currentGroup = currentLine.replaceFirst("\\[", "").replaceFirst("\\]", "");
        return currentGroup;
    }

    private String generateCommand(String fileFullPath, String currentGroup, String currentLine) {
        String[] split = currentLine.split("\\=");
        String key = split[0];
        String value = split[1];

        String command = String.format("%s --file '%s' --group '%s' --key '%s' '%s'",
                kwriteconfigCommand,
                fileFullPath,
                currentGroup,
                key,
                value);
        return command;
    }

    private List<String> readLines() {
        try {
            return Files.readAllLines(targetRcFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read file - " + targetRcFile, e);
        }
    }
}

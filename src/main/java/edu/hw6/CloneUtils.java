package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloneUtils {
    private static final String COPY_PATTERN = " — копия (%d)%s";

    private CloneUtils() {
    }

    public static void cloneFile(Path path) {
        File file = new File(String.valueOf(path));
        if (!file.exists()) {
            throw new IllegalArgumentException("path doesn't exists '" + path + "'");
        }
        String fileNameWithExtension = String.valueOf(path.getFileName());
        int dot = fileNameWithExtension.lastIndexOf('.');
        String format = fileNameWithExtension.substring(dot);
        String parent = path.getParent().toString();
        String name = fileNameWithExtension.substring(0, dot);
        File copy = getCopy(parent, name, format);

        try {
            Files.copy(path, copy.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getCopy(String parent, String name, String format) {
        File copy;
        int copyCount = 0;
        do {
            copyCount++;
            copy = new File(parent, name + String.format(COPY_PATTERN, copyCount, format));
        } while (copy.exists());
        return copy;
    }
}

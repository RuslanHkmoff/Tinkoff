package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public class FilterUtils {
    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static final AbstractFilter WRITABLE = Files::isWritable;

    private FilterUtils() {
    }

    public static AbstractFilter largerThan(long number) {
        return path -> Files.size(path) > number;
    }

    public static AbstractFilter magicNumber(int... attributes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                if (fileBytes.length < attributes.length) {
                    return false;
                }

                for (int i = 0; i < attributes.length; i++) {
                    if ((byte) attributes[i] != fileBytes[i]) {
                        return false;
                    }
                }

                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

    }

    public static AbstractFilter globMatches(String glob) {
        return path -> {
            FileSystem fs = path.getFileSystem();
            final PathMatcher matcher = fs.getPathMatcher("glob:" + glob);
            return matcher.matches(path.getFileName());
        };
    }

    public static AbstractFilter regexContains(String regex) {
        return path -> {
            String fileName = path.getFileName().toString();
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(fileName).find();
        };
    }
}

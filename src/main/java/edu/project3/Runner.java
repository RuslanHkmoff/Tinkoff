package edu.project3;

import edu.project3.analyser.Analyser;
import edu.project3.analyser.LogAnalyser;
import edu.project3.converter.AdocConverter;
import edu.project3.converter.Converter;
import edu.project3.converter.MarkdownConverter;
import edu.project3.parser.LogParser;
import edu.project3.parser.Parser;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings("InnerAssignment")
public class Runner {
    private static final String PATH = "--path";
    private static final String FROM = "--from";
    private static final String TO = "--to";
    private static final String FORMAT = "--format";
    private static final String MD = "markdown";
    private static final String ADOC = "adoc";

    private Runner() {
    }

    public static void run(String[] args) {
        String path = null;
        String from = null;
        String to = null;
        String format = null;
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case PATH -> path = args[i + 1];
                case FROM -> from = args[i + 1];
                case TO -> to = args[i + 1];
                case FORMAT -> format = args[i + 1];
                default -> throw new IllegalArgumentException("Unexpected parameter: '" + args[i] + "");
            }
        }
        validateArguments(path, format);
        Parser<Log> parser;
        if (path.startsWith("http://") || path.startsWith("https://")) {
            parser = new LogParser(path);
        } else {
            parser = new LogParser(Path.of(path));
        }
        List<Log> logs = parser.parse();
        Analyser<Log> analyser = new LogAnalyser();
        Converter converter;
        if (MD.equalsIgnoreCase(format)) {
            converter = new MarkdownConverter();
        } else {
            converter = new AdocConverter();
        }
        String statistics = converter.convert(analyser.getStatistic(logs, from, to));
    }

    private static void validateArguments(String path, String format) {
        if (path == null) {
            throw new IllegalArgumentException("path is required");
        }
        if (!MD.equalsIgnoreCase(format) && !ADOC.equalsIgnoreCase(format)) {
            throw new IllegalArgumentException("format should be adoc or markdow, actual: '" + path + "'");
        }
    }
}

package edu.project3.parser;

import edu.project3.Log;
import edu.project3.parser.exception.ParseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements Parser<Log> {
    private static final String LOG_REGEX =
        "^(\\S+) (\\S+) (\\S+) \\[([^\\]]+)\\] \"(\\S+) (\\S+) ([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$";
    private static final int ADDR_ID = 1;
    private static final int USER_ID = 3;
    private static final int TIME_ID = 4;
    private static final int METHOD_ID = 5;
    private static final int RESOURCE_ID = 6;
    private static final int PROTOCOL_ID = 7;
    private static final int STATUS_ID = 8;
    private static final int SIZE_ID = 9;
    private static final int REFER_ID = 10;
    private static final int AGENT_ID = 11;

    private final Path pathToFile;
    private final String url;

    public LogParser(String url) {
        this.url = url;
        this.pathToFile = null;
    }

    public LogParser(Path pathToFile) {
        this.pathToFile = pathToFile;
        this.url = null;
    }

    @Override
    public List<Log> parse() {
        List<Log> parsed = new ArrayList<>();
        if (pathToFile != null) {
            parsed = parseFromFile();
        } else if (url != null) {
            parsed = parseFromUrl();
        }
        return parsed;
    }

    private List<Log> parseFromUrl() {
        HttpClient client = HttpClient.newHttpClient();
        List<Log> parsed = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String[] splitBody = response.body().split("\n");
            Arrays.stream(splitBody).forEach(log -> parsed.add(parseLog(log)));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }

    private List<Log> parseFromFile() {
        List<Log> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(parseLog(line));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Log parseLog(String log) {
        Pattern pattern = Pattern.compile(LOG_REGEX);
        Matcher matcher = pattern.matcher(log);
        try {
            matcher.find();
            return new Log(
                matcher.group(ADDR_ID),
                matcher.group(USER_ID),
                matcher.group(TIME_ID),
                matcher.group(METHOD_ID),
                matcher.group(RESOURCE_ID),
                matcher.group(PROTOCOL_ID),
                Integer.parseInt(matcher.group(STATUS_ID)),
                Long.parseLong(matcher.group(SIZE_ID)),
                matcher.group(REFER_ID),
                matcher.group(AGENT_ID)
            );
        } catch (Exception e) {
            throw new ParseException("Can't parse log: '" + log + "'", e);
        }
    }
}

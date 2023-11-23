package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final String BASE_URI = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String TITLE_URI = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private final String uri;

    public HackerNews() {
        this.uri = BASE_URI;
    }

    public HackerNews(String uri) {
        this.uri = uri;
    }

    public long[] hackerNewsTopStories() {
        HttpClient client = HttpClient.newHttpClient();
        long[] result;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = parseBody(response.body());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            return new long[0];
        }
        return result;
    }

    public String news(long id) {
        HttpClient client = HttpClient.newHttpClient();
        String result;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(String.format(TITLE_URI, id))).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = parseTitle(response.body());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            return "";
        }
        return result;
    }

    private String parseTitle(String body) {
        Pattern pattern = Pattern.compile(".*?\"title\":(\"[^\"]+\").*");
        Matcher matcher = pattern.matcher(body);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group(1));
        }
        return result.toString();
    }

    protected long[] parseBody(String body) {
        String[] splitBody = body.split("[,\\[\\]]");
        long[] result = new long[splitBody.length - 1];
        for (int i = 1; i < splitBody.length; i++) {
            result[i - 1] = Long.parseLong(splitBody[i]);
        }
        return result;
    }
}

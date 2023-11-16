package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HackerNewsTest {
    @Test
    @DisplayName("test topStories")
    void test1() {
        HackerNews hackerNews = new HackerNewsMock();
        long[] expected = new long[] {38290145L, 38294203L, 38292553L, 38290613L, 38291139L, 38291199L};
        assertThat(hackerNews.hackerNewsTopStories()).isEqualTo(expected);
    }

    @Test
    @DisplayName("test news")
    void test2() {
        HackerNews hackerNews = new HackerNews();
        String expected = "\"The real realtime preemption end game\"";
        assertThat(hackerNews.news(38290145)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test invalid uri")
    void test3() {
        HackerNews hackerNews = new HackerNews("https://hackkker-news.com/v0/topstories.json");
        long[] empty = new long[0];
        assertThat(hackerNews.hackerNewsTopStories()).isEqualTo(empty);
    }

    static class HackerNewsMock extends HackerNews {
        @Override
        public long[] hackerNewsTopStories() {
            String body = "[38290145,38294203,38292553,38290613,38291139,38291199]";
            return parseBody(body);
        }
    }
}

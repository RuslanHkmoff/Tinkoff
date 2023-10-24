package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarketImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    private StockMarketImpl market;

    @BeforeEach
    void setUp() {
        Stock[] stocks = new Stock[] {
            new Stock(25, "stock1", 1),
            new Stock(30, "stock2", 2),
            new Stock(20, "stock3", 3)};
        market = new StockMarketImpl();
        for (Stock stock : stocks) {
            market.add(stock);
        }
    }

    @Test
    @DisplayName("Test mostValuableStock")
    void test1() {
        Stock expected = new Stock(30, "stock2", 2);
        assertThat(market.mostValuableStock()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test remove and mostValuableStock")
    void test2() {
        market.remove(new Stock(30, "stock2", 2));
        Stock expected = new Stock(25, "stock1", 1);
        assertThat(market.mostValuableStock()).isEqualTo(expected);
    }
}

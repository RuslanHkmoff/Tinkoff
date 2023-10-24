package edu.hw3.Task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class StockMarketImpl implements StockMarket {
    private final Queue<Stock> priorityQueue;

    public StockMarketImpl() {
        this.priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(b.getPrice(), a.getPrice()));
    }

    @Override
    public void add(Stock stock) {
        priorityQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        priorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return priorityQueue.peek();
    }
}

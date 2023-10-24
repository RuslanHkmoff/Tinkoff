package edu.hw3.Task6;

import java.util.Objects;

public class Stock {
    private int price;
    private final String name;
    private final long id;

    public Stock(int price, String name, long id) {
        this.price = price;
        this.name = name;
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        Stock castOther = (Stock) other;
        return castOther.getPrice() == price
            && castOther.getName().equals(name)
            && castOther.getId() == id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name, id);
    }

    @Override
    public String toString() {
        return "Stock{" + "price=" + price
            + ", name='" + name + '\''
            + ", id=" + id + '}';
    }
}

package edu.hw2.Task1;

public class Constant implements Expression {
    private final double constant;

    public Constant(double constant) {
        this.constant = constant;
    }

    @Override
    public double evaluate() {
        return constant;
    }

    @Override
    public String toString() {
        return Double.toString(constant);
    }
}

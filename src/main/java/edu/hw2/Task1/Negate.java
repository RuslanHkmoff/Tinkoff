package edu.hw2.Task1;

public class Negate implements Expression {
    private final Expression expr;

    public Negate(Expression expr) {
        this.expr = expr;
    }

    @Override
    public double evaluate() {
        return -expr.evaluate();
    }

    @Override public String toString() {
        return "(-" + expr.toString() + ")";
    }
}

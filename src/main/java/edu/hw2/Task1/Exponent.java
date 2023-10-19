package edu.hw2.Task1;

public class Exponent extends AbstractBinaryOperation {
    public Exponent(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected double makeOperation(double x, double y) {
        return Math.pow(x, y);
    }

    @Override
    protected String getOperationSymbol() {
        return "**";
    }
}

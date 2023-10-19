package edu.hw2.Task1;

public abstract class AbstractBinaryOperation implements Expression {
    private final Expression left;
    private final Expression right;

    public AbstractBinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {
        return makeOperation(left.evaluate(), right.evaluate());
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + getOperationSymbol() + " " + right.toString() + ")";
    }

    protected abstract double makeOperation(double x, double y);

    protected abstract String getOperationSymbol();
}

package edu.hw2.Task1;

public class Multiplication extends AbstractBinaryOperation {

    public Multiplication(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected double makeOperation(double x, double y) {
        return x * y;
    }

    @Override
    protected String getOperationSymbol() {
        return "*";
    }
}

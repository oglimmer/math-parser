package de.oglimmer.math;

public interface Expression {

    Expression add(Expression toAdd);

    double resolve();
}

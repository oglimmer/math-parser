package de.oglimmer.math.astnode;

import java.util.Map;

public interface Expression extends ASTNode {

    Expression add(ASTNode toAdd);

    double resolve(Map<String, Double> vars);

    Expression simplify();

    default void validate() {
    }
}

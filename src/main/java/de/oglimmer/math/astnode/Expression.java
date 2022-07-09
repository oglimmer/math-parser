package de.oglimmer.math.astnode;

public interface Expression extends ASTNode {

    Expression add(ASTNode toAdd);

    long resolve();

    default void validate() {
    }
}

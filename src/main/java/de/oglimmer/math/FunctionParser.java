package de.oglimmer.math;


import de.oglimmer.math.astnode.ASTBuilder;
import de.oglimmer.math.astnode.Expression;
import de.oglimmer.math.tokenize.LexicalAnalyzer;
import de.oglimmer.math.tokenize.Token;

import java.util.HashMap;
import java.util.List;

public class FunctionParser {

    private static final boolean debug = false;

    public static void main(String... args) {
        if (args == null || args.length < 1 || args[0] == null) {
            System.out.println("usage: 1st parameter must be a mathematical function (e.g. \"2+3\")");
            return;
        }
        String input = args[0];
        Expression exp = new FunctionParser().parse(input);
        System.out.println(exp.resolve());
    }

    public Expression parse(String input) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        ASTBuilder astBuilder = new ASTBuilder();

        List<Token> tokens = lexicalAnalyzer.parseToTokens(input);
        debug(tokens);
        Expression expression = astBuilder.tokensToExpression(tokens);
        debug(expression);
        return expression;
    }

    private void debug(Object msg) {
        if (debug) {
            System.out.println(msg);
        }
    }

}

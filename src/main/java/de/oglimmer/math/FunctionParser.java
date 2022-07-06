package de.oglimmer.math;


import de.oglimmer.math.astnode.Expression;
import de.oglimmer.math.token.Token;

import java.util.HashMap;
import java.util.List;

public class FunctionParser {

    private static boolean debug = false;

    public static void main(String... args) {
        if (args == null || args.length < 1 || args[0] == null) {
            System.out.println("usage: needs parameter with math function");
            return;
        }
        String input = args[0];
        HashMap<String, Double> vars = createVarMap(args);
        Expression exp = new FunctionParser().parse(input);
        System.out.println(exp.resolve(vars));
    }

    private static HashMap<String, Double> createVarMap(String[] args) {
        HashMap<String, Double> vars = new HashMap<>();
        for (int i = 2; i < args.length; i += 2) {
            if (args[i - 1] != null && args[i] != null) {
                vars.put(args[i - 1], Double.parseDouble(args[i]));
            }
        }
        return vars;
    }

    private LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
    private ASTBuilder astBuilder = new ASTBuilder();
    private TokenBuilder tokenBuilder = new TokenBuilder();

    public Expression parse(String input) {
        List<String> tokenizedStrings = lexicalAnalyzer.parseToTokens(input);
        debug(tokenizedStrings);
        List<Token> tokens = tokenBuilder.convert(tokenizedStrings);
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

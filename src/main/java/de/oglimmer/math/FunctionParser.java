package de.oglimmer.math;


public class FunctionParser {

    public static void main(String... args) {
        String input = args[0];
        Expression n = new FunctionParser().parse(input);
        System.out.println(n);
    }

    private LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

    public Expression parse(String input) {
        Expression returnExpression = null;
        for (int pos = 0; pos < input.length(); pos++) {
            returnExpression = processCharacter(input, returnExpression, pos);
        }
        return returnExpression;
    }

    private Expression processCharacter(String input, Expression previousExpression, int pos) {
        char currentC = input.charAt(pos);
        Character nextC = pos < input.length() - 1 ? input.charAt(pos + 1) : null;
        lexicalAnalyzer.read(currentC, nextC);
        if (lexicalAnalyzer.tokenCompleted()) {
            Expression newExpression = ExpressionBuilder.fromString(lexicalAnalyzer.nextToken());
            previousExpression = addExpression(previousExpression, newExpression);
        }
        return previousExpression;
    }

    private Expression addExpression(Expression previousExpression, Expression newExpression) {
        if (previousExpression == null) {
            previousExpression = newExpression;
        } else {
            previousExpression = previousExpression.add(newExpression);
        }
        return previousExpression;
    }

}

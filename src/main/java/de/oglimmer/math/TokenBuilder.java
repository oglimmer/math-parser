package de.oglimmer.math;

import de.oglimmer.math.token.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class TokenBuilder {
    public List<Token> convert(List<String> tokens) {
        return tokens.stream().map(this::fromString).collect(Collectors.toList());
    }

    private Token fromString(String token) {
        Class<? extends Token> clazz;
        if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
            clazz = OperatorToken.class;
        } else if (isNumber(token)) {
            clazz = NumberToken.class;
        } else if (token.equals("(") || token.equals(")")) {
            clazz = ParenthesisToken.class;
        } else if (isAlpha(token)) {
            clazz = VariableToken.class;
        } else {
            throw new RuntimeException("cannot convert '" + token + "' to Node");
        }
        try {
            return clazz.getConstructor(new Class[]{String.class}).newInstance(token);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAlpha(String token) {
        return token.matches("^\\w+$");
    }

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

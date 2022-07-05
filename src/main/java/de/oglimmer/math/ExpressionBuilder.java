package de.oglimmer.math;

public class ExpressionBuilder {
    public static Expression fromString(String s) {
        if (s.equals("+")) {
            return Operation.PLUS;
        } else if (s.equals("-")) {
            return Operation.MINUS;
        } else if (isNumber(s)) {
            return new Number(Double.parseDouble(s));
        } else {
            throw new RuntimeException("cannot convert '" + s + "' to Node");
        }
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

package de.oglimmer.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidInputTest {

    public final FunctionParser functionParser = new FunctionParser();

    @Test
    public void missingOpen() {
        InvalidFormulaException thrown = Assertions.assertThrows(InvalidFormulaException.class, () -> {
            functionParser.parse("23+45)");
        });
        Assertions.assertEquals("Missing opening (", thrown.getMessage());
    }

    @Test
    public void missingClosing() {
        InvalidFormulaException thrown = Assertions.assertThrows(InvalidFormulaException.class, () -> {
            functionParser.parse("(23+45");
        });
        Assertions.assertEquals("Missing closing )", thrown.getMessage());
    }

    @Test
    public void doubleOpen() {
        InvalidFormulaException thrown = Assertions.assertThrows(InvalidFormulaException.class, () -> {
            functionParser.parse("((23+45)");
        });
        Assertions.assertEquals("Missing closing )", thrown.getMessage());
    }

    @Test
    public void doubleClosing() {
        InvalidFormulaException thrown = Assertions.assertThrows(InvalidFormulaException.class, () -> {
            functionParser.parse("(23+45))");
        });
        Assertions.assertEquals("Missing opening (", thrown.getMessage());
    }

    @Test
    public void illegalOpenAfterClosing() {
        InvalidFormulaException thrown = Assertions.assertThrows(InvalidFormulaException.class, () -> {
            functionParser.parse("(23+45)(3+4)");
        });
        Assertions.assertEquals("Unexpected character ( detected. Only operator or closing parenthesis allowed here.", thrown.getMessage());
    }

}
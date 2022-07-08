package de.oglimmer.math.token;

import de.oglimmer.math.fsm.Action;
import de.oglimmer.math.fsm.FSM;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

    public List<Token> parseToTokens(String input) {
        FSM fsm = new FSM();
        List<Token> nodes = new ArrayList<>();
        for (int pos = 0; pos < input.length(); pos++) {
            char currentCharacter = input.charAt(pos);
            Character nextCharacter = pos < input.length() - 1 ? input.charAt(pos + 1) : null;
            Token token = fsm.transition(new Action(currentCharacter, nextCharacter));
            if (token != null) {
                nodes.add(token);
            }
        }
        return nodes;
    }
}

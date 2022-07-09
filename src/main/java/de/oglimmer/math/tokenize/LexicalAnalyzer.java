package de.oglimmer.math.tokenize;

import de.oglimmer.fsm.FSM;
import de.oglimmer.math.tokenize.stateImpl.EmptyState;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

    public List<Token> parseToTokens(String input) {
        FSM<ReadCharacterEvent, Token> fsm = new FSM<>(new EmptyState());
        List<Token> nodes = new ArrayList<>();
        for (int pos = 0; pos < input.length(); pos++) {
            char currentCharacter = input.charAt(pos);
            Character nextCharacter = pos < input.length() - 1 ? input.charAt(pos + 1) : null;
            Token token = fsm.transition(new ReadCharacterEvent(currentCharacter, nextCharacter));
            if (token != null) {
                nodes.add(token);
            }
        }
        return nodes;
    }
}

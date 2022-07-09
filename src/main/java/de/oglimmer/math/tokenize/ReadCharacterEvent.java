package de.oglimmer.math.tokenize;

import de.oglimmer.fsm.Event;

public class ReadCharacterEvent implements Event {

    private final char readCharacter;
    private final Character nextCharacter;

    public ReadCharacterEvent(char readCharacter, Character nextCharacter) {
        this.readCharacter = readCharacter;
        this.nextCharacter = nextCharacter;
    }

    public char getReadCharacter() {
        return readCharacter;
    }

    public Character getNextCharacter() {
        return nextCharacter;
    }
}

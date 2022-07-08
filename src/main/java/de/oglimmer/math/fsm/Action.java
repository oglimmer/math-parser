package de.oglimmer.math.fsm;

public class Action {

    private char readCharacter;
    private Character nextCharacter;

    public Action(char readCharacter, Character nextCharacter) {
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

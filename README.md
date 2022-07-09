# A mathematical function parser for java

## Supported syntax

It can parse `+ - * / ^ ( )` as in `53 * 34` or `(2+3)*4^2`

It can parse `sqrt log logten sin cos tan asin acos atan` as in `sqrt(9) or cos(343)` (`log` is log to base of 2, and `logten` is log to the base of 10)

It can parse `pi e` to return the constants pi and e.

Finally it can parse any variable name matching `^\w+$`.

## How to use it

```bash
mvn compile
java -cp target/classes de.oglimmer.math.FunctionParser "((2+3)*-3)+2+x*y*sin(pi/2)" "x" "-34" "y" "3"
```

(First parameter is the function, any 2nd and 3rd is a variable and its value)

## How it works

### FSM

This project defines a generic FSM framework in the [package fsm](src/main/java/de/oglimmer/fsm). It's used by
[LexicalAnalyzer.java](src/main/java/de/oglimmer/math/tokenize/LexicalAnalyzer.java), where this package also implements
the Event and Action interfaces defined by the FSM framework and the [package stateImpl](src/main/java/de/oglimmer/math/tokenize/stateImpl) implements
all the FSM states. 

### Math parser

Step 1 - the lexical analysis (aka the tokenizing) is done in [LexicalAnalyzer.java](src/main/java/de/oglimmer/math/tokenize/LexicalAnalyzer.java) 
and the resulting objects are [Token.java](src/main/java/de/oglimmer/math/tokenize/Token.java).

Step 2 - the conversion from tokens to AST is done from a method inside of [Token.java](src/main/java/de/oglimmer/math/tokenize/Token.java) `public ASTNode toASTNode() {...}` and by using the method `Expression add(ASTNode toAdd)` on the first [Expression.java](src/main/java/de/oglimmer/math/astnode/Expression.java) with the subsequent [ASTNode.java](src/main/java/de/oglimmer/math/astnode/ASTNode.java).

![Diagram](diagram.png)

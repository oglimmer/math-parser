# A mathematical function parser for java

## Supported syntax

It can parse `+ - * / ( )` and any variable name matching `^\w+$`

## How to use it

```bash
mvn compile
java -cp target/classes de.oglimmer.math.FunctionParser "((2+3)*-3)+2+x*y" "x" "-34" "y" "3"
```

package ast.exception.semantic;

import ast.exception.AstBaseException;

public class UnknownSymbolException extends AstBaseException {
    /**
     * Default constructor
     */
    public UnknownSymbolException(String symbolName, int lineNumber) {
        super("Unknown symbol \"" + symbolName + "\" line " + lineNumber);
    }
}

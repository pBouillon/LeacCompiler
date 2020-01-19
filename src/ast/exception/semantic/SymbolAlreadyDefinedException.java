package ast.exception.semantic;

import ast.exception.AstBaseException;
import symbolTable.symbol.Symbol;

public class SymbolAlreadyDefinedException extends AstBaseException {
    /**
     * Default constructor
     */
    public SymbolAlreadyDefinedException(Symbol symbol) {
        super("Symbol " + symbol.getIdf() + " already defined line " + symbol.getLineNumber());
    }
}

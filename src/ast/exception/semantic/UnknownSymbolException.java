package ast.exception.semantic;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class UnknownSymbolException extends AstBaseException {
    /**
     * Default constructor
     */
    public UnknownSymbolException(String symbolName, Tree node) {
        super("Unknown symbol \"" + symbolName + "\" line " + node.getLine());
    }
}

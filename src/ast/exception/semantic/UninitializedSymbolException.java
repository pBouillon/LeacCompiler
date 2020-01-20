package ast.exception.semantic;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class UninitializedSymbolException extends AstBaseException {
    public UninitializedSymbolException(String idf, Tree node) {
        super("Using an unitialized variable \"" + idf + "\" line " + node.getLine());
    }
}

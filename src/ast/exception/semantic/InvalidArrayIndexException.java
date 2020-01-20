package ast.exception.semantic;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class InvalidArrayIndexException extends AstBaseException {
    /**
     * Default constructor
     */
    public InvalidArrayIndexException(Tree node, String foundIndex) {
        super("Index should be of type 'int' line " + node.getLine()+ " (found '" + foundIndex + "')");
    }
}

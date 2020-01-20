package ast.exception.semantic;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class TypeMismatchException extends AstBaseException {
    /**
     * Default constructor
     */
    public TypeMismatchException(String expectedType, String actualType, Tree currentNode) {
        super("Expected type " + expectedType + " but found " + actualType + " line " + currentNode.getLine());
    }

    public TypeMismatchException(Tree currentNode) {
        super("Invalid type line " + currentNode.getLine());
    }
}

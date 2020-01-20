package ast.exception.common;

import ast.exception.AstBaseException;

public class UnexpectedChildException extends AstBaseException {
    /**
     * Default constructor
     */
    public UnexpectedChildException(String parentNode, String unexpectedNode) {
        super("Unexpected node \" " + unexpectedNode + " \" in " + parentNode + " node");
    }
}

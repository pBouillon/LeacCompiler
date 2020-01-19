package ast.exception.operation;

import ast.exception.AstBaseException;

public class BadOperationNameException extends AstBaseException {
    /**
     * @param operation_name
     */
    public BadOperationNameException(String operation_name) {
        super("Unrecognized operation : " + operation_name);
    }
}

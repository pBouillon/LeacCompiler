package ast.exception.root;

import ast.exception.AstBaseException;

/**
 * ast.root.exception.BadNodeNameException is the default template for exceptions ROOT node exception,
 * relative to its name
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class BadNodeNameException extends AstBaseException {

    /**
     * Default constructor
     *
     * @param message exception message
     */
    public BadNodeNameException(String message) {
        super(message);
    }

}

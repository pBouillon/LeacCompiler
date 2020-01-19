package ast.exception.common;

import ast.exception.AstBaseException;

/**
 * ast.root.exception.AstBaseException is the default template for exceptions ROOT node exception,
 * relative to its childrens count
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class BadChildrenCountException extends AstBaseException {

    /**
     * Default constructor
     */
    public BadChildrenCountException(int expectedSize, int actualSize) {
        super("Bad children amount, expected " + expectedSize + " but found " + actualSize);
    }

}

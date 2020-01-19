package ast.exception.common;

import ast.exception.AstBaseException;
import utils.AstNodes;

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
     * @param awaited_name
     * @param bad_name
     */
    public BadNodeNameException(String awaited_name, String bad_name) {
        super("Node name should be " + awaited_name + " but is " + bad_name);
    }

}

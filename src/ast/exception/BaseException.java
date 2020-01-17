package ast.exception;

/**
 * ast.exception.BaseException is the default template for exceptions
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public abstract class BaseException extends Exception {

    /**
     * Default constructor
     *
     * @param message exception message
     */
    protected BaseException(String message) {
        super(message);
    }

}

package ast;

import org.antlr.runtime.tree.Tree;

/**
 * ast.RootNode is the root node of the built AST
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class RootNode extends BaseNode {

    /**
     * Default constructor for the root node
     *
     * @param _currentNode ANTLR raw AST
     */
    public RootNode(Tree _currentNode) {
        super(_currentNode);
    }

}

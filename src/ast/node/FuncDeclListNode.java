package ast.node;

import ast.exception.root.BadChildrenCountException;
import org.antlr.runtime.tree.Tree;

/**
 * ast.node.FuncDeclListNode is the node in which all functions are declared
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class FuncDeclListNode extends BaseNode {

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public FuncDeclListNode(Tree _currentNode) throws BadChildrenCountException {
        super(_currentNode);
    }

    @Override
    protected void extractChildren() {

    }

}

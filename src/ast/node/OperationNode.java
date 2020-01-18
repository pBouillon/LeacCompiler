package ast.node;

import ast.exception.AstBaseException;
import ast.exception.root.BadChildrenCountException;
import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;

/**
 * ast.node.OperationNode is a node for operations
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */

public abstract class OperationNode extends BaseNode {

    protected OperationNode leftNode;

    protected OperationNode rightNode;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    OperationNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        int allowedChildrenAmount = 2;

        if (children.size() != allowedChildrenAmount) {
            throw new BadChildrenCountException(allowedChildrenAmount, children.size());
        }
    }


}

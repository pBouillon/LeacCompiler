package ast.node.operation;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.factory.OperationNodeFactory;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;

/**
 * ast.node.operation.OperationNode is a node for operations
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
    protected OperationNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        int allowedChildrenAmount = 2;

        if (children.size() != allowedChildrenAmount) {
            throw new BadChildrenCountException(allowedChildrenAmount, children.size());
        }
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        int i = 0;

        leftNode = OperationNodeFactory.createOperationNode(children.get(i++));
        rightNode = OperationNodeFactory.createOperationNode(children.get(i));

    }


}

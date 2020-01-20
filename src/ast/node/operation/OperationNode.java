package ast.node.operation;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.exception.operation.BadOperationNameException;
import ast.factory.OperationNodeFactory;
import ast.node.BaseNode;
import ast.node.constant.ConstantNumericNode;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

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

    protected BaseNode leftNode;

    protected BaseNode rightNode;

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

        leftNode = extractNode(children.get(0));
        rightNode = extractNode(children.get(1));

    }

    private BaseNode extractNode(Tree node) throws AstBaseException {
        if (node.toString().equalsIgnoreCase(AstNodes.PLUS_NODE)
                || node.toString().equalsIgnoreCase(AstNodes.MULT_NODE)
                || node.toString().equalsIgnoreCase(AstNodes.POW_NODE)
                || node.toString().equalsIgnoreCase(AstNodes.MINUS_NODE)) {
            return OperationNodeFactory.createOperationNode(node);
        }
        else if (node.toString().equalsIgnoreCase(AstNodes.CSTE_N)) {
            return new ConstantNumericNode(node);
        }
        else {
            throw new BadOperationNameException(node.toString());
        }
    }

    @Override
    protected void exitNode() throws AstBaseException {
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
    }

}

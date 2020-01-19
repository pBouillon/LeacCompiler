package ast.node.operation;

import ast.exception.AstBaseException;
import ast.factory.OperationNodeFactory;
import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;

/**
 * ast.node.operation.EqNode is the equal operator node
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class EqNode extends OperationNode {

    private ArrayList<OperationNode> subOperations;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public EqNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    protected void exitNode() throws AstBaseException {

    }

    @Override
    protected void extractChildren() throws AstBaseException {
        for (Tree child : children) {
            subOperations.add(OperationNodeFactory.createOperationNode(child));
        }
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }
}

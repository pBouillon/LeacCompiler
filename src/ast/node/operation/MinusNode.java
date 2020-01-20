package ast.node.operation;

import ast.exception.AstBaseException;
import ast.factory.OperationNodeFactory;
import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;

/**
 * ast.node.operation.MinusNode is the minus operator node
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */

public class MinusNode extends OperationNode {

    private ArrayList<OperationNode> subOperations;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public MinusNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        return null;
    }

    @Override
    protected void exitNode() throws AstBaseException {

    }

    @Override
    protected void extractChildren() throws AstBaseException {
        for (Tree child: children) {
            subOperations.add(OperationNodeFactory.createOperationNode(child));
        }
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    @Override
    protected void performSemanticControls() throws AstBaseException {

    }
}

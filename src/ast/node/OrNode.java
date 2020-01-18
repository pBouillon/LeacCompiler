package ast.node;

import ast.exception.AstBaseException;
import ast.exception.root.BadChildrenCountException;
import ast.exception.root.BadNodeNameException;
import ast.factory.OperationNodeFactory;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

/**
 * ast.node.OrNode is the OR operator node
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */

public class OrNode extends OperationNode {

    private ArrayList<OperationNode> subOperations;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public OrNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
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
}

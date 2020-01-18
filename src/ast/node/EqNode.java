package ast.node;

import ast.exception.AstBaseException;
import ast.exception.root.BadChildrenCountException;
import ast.exception.root.BadNodeNameException;
import ast.factory.OperationNodeFactory;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

/**
 * ast.node.EqNode is the equal operator node
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
    protected void extractChildren() throws AstBaseException {
        for (Tree child: children) {
            subOperations.add(OperationNodeFactory.createOperationNode(child));
        }
    }
}
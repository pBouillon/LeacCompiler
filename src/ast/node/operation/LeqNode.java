package ast.node.operation;

import ast.exception.AstBaseException;
import ast.factory.OperationNodeFactory;
import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;

/**
 * ast.node.operation.LeqNode is the "less or equal" operator node
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class LeqNode extends OperationNode {

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public LeqNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        return leftNode.generateCode(prefix) + " <= " + rightNode.generateCode(prefix);
    }

    @Override
    protected void performSemanticControls() throws AstBaseException {

    }
}

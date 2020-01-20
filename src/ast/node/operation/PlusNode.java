package ast.node.operation;

import ast.exception.AstBaseException;
import ast.exception.operation.BadOperationNameException;
import ast.factory.OperationNodeFactory;
import ast.node.BaseNode;
import ast.node.constant.ConstantNumericNode;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

/**
 * ast.node.operation.PlusNode is the addition operator node
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */

public class PlusNode extends OperationNode {


    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public PlusNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        return leftNode.generateCode(prefix) + " + " + rightNode.generateCode(prefix);
    }

    @Override
    protected void performSemanticControls() throws AstBaseException {
    }
}

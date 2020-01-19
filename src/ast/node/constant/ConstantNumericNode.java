package ast.node.constant;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

public class ConstantNumericNode extends ConstantNode {

    private int value;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected ConstantNumericNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.CSTE_N)) {
            throw new BadNodeNameException(AstNodes.CSTE_N, nodeName);
        }
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        value = Integer.parseInt(children.get(0).toString());
    }

}

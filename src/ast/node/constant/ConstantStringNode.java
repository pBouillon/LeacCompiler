package ast.node.constant;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

public class ConstantStringNode extends ConstantNode {

    private String value;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected ConstantStringNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.CSTE_S)) {
            throw new BadNodeNameException(AstNodes.CSTE_S, nodeName);
        }
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        value = children.get(0).toString();
    }
}

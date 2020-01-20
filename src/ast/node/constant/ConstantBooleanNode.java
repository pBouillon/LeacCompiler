package ast.node.constant;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

public class ConstantBooleanNode extends ConstantNode {

    private boolean value;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected ConstantBooleanNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.CSTE_B)) {
            throw new BadNodeNameException(AstNodes.CSTE_B, nodeName);
        }
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        value = Boolean.parseBoolean(children.get(0).toString());
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        return String.valueOf(value);
    }

}

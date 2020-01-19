package ast.node.constant;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;

public abstract class ConstantNode extends BaseNode {
    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected ConstantNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        int expectedChildren = 1;

        if (children.size() != expectedChildren) {
            throw new BadChildrenCountException(expectedChildren, children.size());
        }
    }

    @Override
    protected void exitNode() throws AstBaseException {
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
    }
}

package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class FuncCallNode extends BaseNode {
    public FuncCallNode(Tree _currentNode) {
        super(_currentNode);
    }

    /**
     *
     */
    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void exitNode() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void checkChildrenAmount() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
    // TODO
    }

    /**
     *
     */
    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }
}

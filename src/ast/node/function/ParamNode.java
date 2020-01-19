package ast.node.function;

import ast.exception.AstBaseException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;

public class ParamNode extends BaseNode {
    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    ParamNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {

    }

    @Override
    protected void exitNode() throws AstBaseException {

    }

    @Override
    protected void extractChildren() throws AstBaseException {

    }

    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }
}

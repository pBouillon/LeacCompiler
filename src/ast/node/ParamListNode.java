package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class ParamListNode extends BaseNode {
    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    ParamListNode(Tree _currentNode) throws AstBaseException {
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

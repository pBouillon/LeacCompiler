package ast.node;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

public class FuncCallNode extends BaseNode {
    public FuncCallNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.FUNC_CALL)) {
            throw new BadNodeNameException(AstNodes.FUNC_CALL, nodeName);
        }
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

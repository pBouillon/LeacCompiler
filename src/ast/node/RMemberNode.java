package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class RMemberNode extends BaseNode {
    public RMemberNode(Tree child) throws AstBaseException {
        super(child);
    }

    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    protected void exitNode() throws AstBaseException {

    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        // TODO
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        // TODO
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    @Override
    protected void performSemanticControls() throws AstBaseException {

    }
}

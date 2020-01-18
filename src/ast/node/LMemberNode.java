package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class LMemberNode extends BaseNode{
    public LMemberNode(Tree child) throws AstBaseException {
        super(child);
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        // TODO
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        // TODO
    }
}

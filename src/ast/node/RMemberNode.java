package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class RMemberNode extends BaseNode {
    public RMemberNode(Tree child) throws AstBaseException {
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

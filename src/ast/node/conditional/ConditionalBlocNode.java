package ast.node.conditional;

import ast.exception.AstBaseException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;

public class ConditionalBlocNode extends BaseNode {
    public ConditionalBlocNode(Tree child) throws AstBaseException {
        super(child);
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
    protected void exitNode() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    /**
     * @param prefix
     */
    @Override
    public String generateCode(String prefix) throws AstBaseException {
        return null;
    }

    /**
     *
     */
    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void performSemanticControls() throws AstBaseException {

    }
}

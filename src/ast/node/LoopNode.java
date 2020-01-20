package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

public class LoopNode extends BaseNode {
    public LoopNode(Tree child) throws AstBaseException {
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
        return prefix+"//<WHILE>\n";
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

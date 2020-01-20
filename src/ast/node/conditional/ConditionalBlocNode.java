package ast.node.conditional;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;

public class ConditionalBlocNode extends BaseNode {

    private BaseNode conditionNode;

    public ConditionalBlocNode(Tree child) throws AstBaseException {
        super(child);
    }

    /**
     *
     */
    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        int allowedChildrenAmount = 1;

        if (children.size() != allowedChildrenAmount) {
            throw new BadChildrenCountException(allowedChildrenAmount, children.size());
        }
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
        // todo: cst_b
        // todo: idf
        // todo: operation
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
        return prefix + conditionNode.generateCode(prefix);
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

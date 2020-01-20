package ast.node;

import ast.exception.AstBaseException;
import ast.node.conditional.ConditionNode;
import org.antlr.runtime.tree.Tree;
import utils.GrammarConstants;

import java.util.concurrent.locks.Condition;

public class LoopNode extends BaseNode {

    private ConditionNode loopCondition;
    private InstrBlocNode instrBlocNode;

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
        loopCondition = new ConditionNode(children.get(0));
        instrBlocNode = new InstrBlocNode(children.get(1));
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
        return prefix + "while (" + loopCondition.generateCode(prefix) + ") " +
                "\n" + prefix +
                "{\n" + instrBlocNode.generateCode(prefix + GrammarConstants.INDENTATION) +
                "}\n";
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

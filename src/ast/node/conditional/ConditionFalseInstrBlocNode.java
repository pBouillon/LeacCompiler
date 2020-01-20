package ast.node.conditional;

import ast.exception.AstBaseException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;

public class ConditionFalseInstrBlocNode extends BaseNode {

    public ArrayList<BaseNode> instructions;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected ConditionFalseInstrBlocNode(Tree _currentNode) throws AstBaseException {
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
        instructions = new ArrayList<>();
    }

    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        return null;
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    @Override
    protected void performSemanticControls() throws AstBaseException {

    }
}

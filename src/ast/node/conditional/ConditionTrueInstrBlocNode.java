package ast.node.conditional;

import ast.exception.AstBaseException;
import ast.node.*;
import ast.node.function.FuncCallNode;
import ast.node.idf.VarAffectNode;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

public class ConditionTrueInstrBlocNode extends BaseNode {

    public ArrayList<BaseNode> instructions;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected ConditionTrueInstrBlocNode(Tree _currentNode) throws AstBaseException {
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

        for(Tree child : children) {
            instructions.add(InstrBlocNode.getInstructionNode(child));
        }
    }

    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        StringBuilder sb = new StringBuilder();

        for (BaseNode instruction : instructions) {
            sb.append(prefix)
                    .append(instruction.generateCode(prefix))
                    .append(";\n");
        }

        return sb.toString();
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    @Override
    protected void performSemanticControls() throws AstBaseException {

    }
}

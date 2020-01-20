package ast.node;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.exception.common.UnexpectedChildException;
import ast.node.conditional.ConditionalBlocNode;
import ast.node.function.FuncCallNode;
import ast.node.idf.VarAffectNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import utils.AstNodes;

import java.util.ArrayList;

/**
 * ast.node.InstrBlocNode is the node in which instructions are defined
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class InstrBlocNode extends BaseNode {

    private ArrayList<BaseNode> instructions;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public InstrBlocNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.INSTR_BLOC)) {
            throw new BadNodeNameException(AstNodes.INSTR_BLOC, nodeName);
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
    protected void exitNode() throws AstBaseException {
        SymbolTableProvider.unwrap();
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {

    }

    @Override
    protected void extractChildren() throws AstBaseException {
        instructions = new ArrayList<>();
        for(Tree child : children) {
            instructions.add(InstrBlocNode.getInstructionNode(child));
        }
    }

    public static BaseNode getInstructionNode(Tree node) throws AstBaseException {
        switch(node.toString()) {
            case AstNodes.WRITE_INSTR:
                return new WriteInstrNode(node);
            case AstNodes.RETURN_INSTR:
                return new ReturnInstrNode(node);
            case AstNodes.FUNC_CALL:
                return new FuncCallNode(node);
            case AstNodes.VAR_AFFECT:
                return new VarAffectNode(node);
            case AstNodes.LOOP:
                return new LoopNode(node);
            case AstNodes.CONDITIONNAL_BLOC:
                return new ConditionalBlocNode(node);
            default:
                throw new UnexpectedChildException(node.toString(), node.toString());
        }
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
        SymbolTableProvider.nest();
    }

    @Override
    protected void performSemanticControls() throws AstBaseException {

    }

}

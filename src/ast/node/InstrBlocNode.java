package ast.node;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
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

    private ArrayList<BaseNode> instructions = new ArrayList<>();

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
        for(Tree child : children) {
            switch(child.toString()) {
                case AstNodes.WRITE_INSTR:
                    instructions.add(new WriteInstrNode(child));
                    break;
                case AstNodes.RETURN_INSTR:
                    instructions.add(new ReturnInstrNode(child));
                    break;
                case AstNodes.FUNC_CALL:
                    instructions.add(new FuncCallNode(child));
                    break;
                case AstNodes.VAR_AFFECT:
                    instructions.add(new VarAffectNode(child));
                case AstNodes.LOOP:
                    instructions.add(new LoopNode(child));
                case AstNodes.CONDITIONNAL_BLOC:
                    instructions.add(new ConditionnalBLocNode(child));
                default:
                    break;
            }
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

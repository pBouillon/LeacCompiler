package ast.node.idf;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.exception.semantic.TypeMismatchException;
import ast.exception.semantic.UnknownSymbolException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import symbolTable.symbol.Symbol;
import symbolTable.symbol.SymbolType;
import utils.AstNodes;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;

/**
 * ast.node.idf.VarDeclListNode is the variables in which all functions are declared
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class VarDeclListNode extends BaseNode {

    private ArrayList<VarDecNode> vars;
    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public VarDeclListNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.VAR_DECL_LIST)) {
            throw new BadNodeNameException(AstNodes.VAR_DECL_LIST, nodeName);
        }
    }

    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    protected void exitNode() throws AstBaseException {
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        vars = new ArrayList<>();
        for(Tree child : children) {
            vars.add(new VarDecNode(child));
        }
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);

        for (VarDecNode var : vars) {
            sb.append(var.generateCode(prefix));
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

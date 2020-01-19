package ast.node.function;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import utils.AstNodes;

import java.util.ArrayList;

/**
 * ast.node.function.FuncDeclListNode is the node in which all functions are declared
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class FuncDeclListNode extends BaseNode {

    private ArrayList<FuncDeclNode> declaredFunctions;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public FuncDeclListNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.FUNC_DECL_LIST)) {
            throw new BadNodeNameException(AstNodes.FUNC_DECL_LIST, nodeName);
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
        declaredFunctions = new ArrayList<>();

        for (Tree child : children) {
            declaredFunctions.add(new FuncDeclNode(child));
        }
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
    }

    @Override
    protected void performSemanticControls() throws AstBaseException {
    }

}

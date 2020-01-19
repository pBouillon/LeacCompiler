package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;

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

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public InstrBlocNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
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
    protected void extractChildren() {

    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
        SymbolTableProvider.addNested();
    }

}

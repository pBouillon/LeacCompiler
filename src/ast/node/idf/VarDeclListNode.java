package ast.node.idf;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

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
    protected void extractChildren() {
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
    }

}

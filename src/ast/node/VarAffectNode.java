package ast.node;

import ast.exception.AstBaseException;
import ast.exception.root.BadChildrenCountException;
import org.antlr.runtime.tree.Tree;

public class VarAffectNode extends BaseNode {

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    VarAffectNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {

    }
}


package ast.node;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

public class ParamListNode extends BaseNode {

    private ArrayList<ParamNode> paramNodes;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    ParamListNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.PARAM_LIST)) {
            throw new BadNodeNameException(AstNodes.PARAM_LIST, nodeName);
        }
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
    }

    @Override
    protected void exitNode() throws AstBaseException {
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        paramNodes = new ArrayList<>();

        for (Tree child : children) {
            paramNodes.add(new ParamNode(child));
        }
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
    }
}

package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

public class LMemberNode extends BaseNode {

    private ArrayList<IDFNode> idfs;


    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    LMemberNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

    }

    /**
     *
     */
    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    protected void exitNode() throws AstBaseException {

    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {}

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
        // Assign each child
        for (Tree child: children) {
            switch (child.toString()) {
                case AstNodes.INDEX:
                    IDFArrayNode ArrayNode = new IDFArrayNode(child);
                    ArrayNode.setName(idfs.get(idfs.size()).toString());
                    idfs.set(idfs.size(), ArrayNode);
                    break;

                default:
                    idfs.add(new IDFNode(child));
            }
        }
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }
}

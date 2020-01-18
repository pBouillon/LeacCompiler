package ast.node;

import ast.exception.AstBaseException;
import ast.exception.root.BadChildrenCountException;
import ast.exception.root.BadNodeNameException;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

public class VarAffectNode extends BaseNode {


    private LMemberNode lMemberNode;
    private RMemberNode rMemberNode;
    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    VarAffectNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.VAR_AFFECT)) {
            throw new BadNodeNameException(AstNodes.VAR_AFFECT, nodeName);
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

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
        // Assert allowed children
        int childrenNumber = 2;

        if (children.size() != childrenNumber) {
            throw new BadChildrenCountException(childrenNumber, children.size());
        }

        // Assign each child
        for (Tree child: children) {
            switch (child.toString()) {
                case AstNodes.LMEMBER:
                    rMemberNode = new RMemberNode(child);
                    break;

                case AstNodes.RMEMBER:
                    lMemberNode = new LMemberNode(child);
                    break;
            }
        }
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }
}


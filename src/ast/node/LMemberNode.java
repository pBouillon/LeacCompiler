package ast.node;

import ast.exception.AstBaseException;
import ast.node.idf.IdfArrayNode;
import ast.node.idf.IdfNode;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

public class LMemberNode extends BaseNode {

    private ArrayList<IdfNode> idfs;


    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public LMemberNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

    }

    /**
     *
     */
    @Override
    protected void extractIdfs() throws AstBaseException {

    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        StringBuilder sb = new StringBuilder();

        sb.append(prefix);

        for (BaseNode node : idfs) {
            sb.append(node.generateCode(prefix));
        }

        return sb.toString();
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
        idfs = new ArrayList<>();

        // Assign each child
        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.ARRAY_INDEX:
                    IdfArrayNode ArrayNode = new IdfArrayNode(child);
                    ArrayNode.setName(idfs.get(idfs.size() - 1).toString());
                    idfs.set(idfs.size()-1, ArrayNode);
                    break;

                default:
                    idfs.add(new IdfNode(child));
            }
        }
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    @Override
    protected void performSemanticControls() throws AstBaseException {

    }
}

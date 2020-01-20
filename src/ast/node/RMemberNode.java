package ast.node;

import ast.exception.AstBaseException;
import ast.factory.OperationNodeFactory;
import ast.node.constant.ConstantBooleanNode;
import ast.node.constant.ConstantNumericNode;
import ast.node.constant.ConstantStringNode;
import ast.node.idf.IdfArrayNode;
import ast.node.idf.IdfNode;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

public class RMemberNode extends BaseNode {

    private ArrayList<BaseNode> items;
    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public RMemberNode(Tree _currentNode) throws AstBaseException {
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
        return null;
    }

    @Override
    protected void exitNode() throws AstBaseException {

    }

    @Override
    protected void checkChildrenAmount() {}

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
        // Assign each child
        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.INDEX:
                    IdfArrayNode ArrayNode = new IdfArrayNode(child);
                    ArrayNode.setName(items.get(items.size()-1).toString());
                    items.set(items.size()-1, ArrayNode);
                    break;
                case AstNodes.CSTE_N:
                    items.add(new ConstantNumericNode(child));
                    break;
                case AstNodes.CSTE_B:
                    items.add(new ConstantBooleanNode(child));
                    break;
                case AstNodes.CSTE_S:
                    items.add(new ConstantStringNode(child));
                    break;
                case AstNodes.AND_NODE:
                case AstNodes.OR_NODE:
                case AstNodes.EQ_NODE:
                case AstNodes.GEQ_NODE:
                case AstNodes.NEQ_NODE:
                case AstNodes.LEQ_NODE:
                case AstNodes.LT_NODE:
                case AstNodes.GT_NODE:
                case AstNodes.DIV_NODE:
                case AstNodes.PLUS_NODE:
                case AstNodes.MULT_NODE:
                case AstNodes.MINUS_NODE:
                case AstNodes.POW_NODE:
                case AstNodes.UMINUS_NODE:
                case AstNodes.NOT_NODE:
                    items.add(OperationNodeFactory.createOperationNode(child));
                    break;


                default:
                    items.add(new IdfNode(child));
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

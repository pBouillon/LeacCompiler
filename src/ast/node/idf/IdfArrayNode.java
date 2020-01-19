package ast.node.idf;

import ast.exception.AstBaseException;
import ast.factory.OperationNodeFactory;
import ast.node.BaseNode;
import ast.node.constant.ConstantNumericNode;
import ast.node.function.FuncCallNode;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

import java.util.ArrayList;

public class IdfArrayNode extends IdfNode {

    private ArrayList<BaseNode> indexes;


    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public IdfArrayNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    /**
     *
     */
    @Override
    protected void exitNode() throws AstBaseException {

    }

    public void setName(String name) {
        this.nodeName = name;
    }

    /**
     *
     */
    @Override
    protected void checkChildrenAmount() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
        // Assign each child
        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.CSTE_N:
                    indexes.add(new ConstantNumericNode(child));

                case AstNodes.FUNC_CALL:
                    indexes.add(new FuncCallNode(child));

                case AstNodes.MULT_NODE:
                case AstNodes.DIV_NODE:
                case AstNodes.POW_NODE:
                case AstNodes.PLUS_NODE:
                    indexes.add(OperationNodeFactory.createOperationNode(child));

                case AstNodes.INDEX:
                    IdfArrayNode ArrayNode = new IdfArrayNode(child);
                    ArrayNode.setName(indexes.get(indexes.size()).toString());
                    indexes.set(indexes.size(), ArrayNode);
                    break;

                case AstNodes.CSTE_B:
                case AstNodes.CSTE_S:
                    // TODO: raise ex

                default:
                    indexes.add(new IdfNode(child));
            }
        }
    }

    /**
     *
     */
    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }
}

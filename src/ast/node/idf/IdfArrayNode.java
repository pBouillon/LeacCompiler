package ast.node.idf;

import ast.exception.AstBaseException;
import ast.exception.semantic.InvalidArrayIndexException;
import ast.factory.OperationNodeFactory;
import ast.node.BaseNode;
import ast.node.constant.ConstantBooleanNode;
import ast.node.constant.ConstantNumericNode;
import ast.node.constant.ConstantStringNode;
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
    protected void checkChildrenAmount() {

    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
        indexes = new ArrayList<>();

        // Assign each child
        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.CSTE_N:
                    indexes.add(new ConstantNumericNode(child));
                    break;

                case AstNodes.FUNC_CALL:
                    indexes.add(new FuncCallNode(child));
                    break;

                case AstNodes.MULT_NODE:
                case AstNodes.DIV_NODE:
                case AstNodes.POW_NODE:
                case AstNodes.PLUS_NODE:
                    indexes.add(OperationNodeFactory.createOperationNode(child));
                    break;
                case AstNodes.UMINUS_NODE:
                    ConstantNumericNode poneytail = new ConstantNumericNode(child.getChild(0));
                    poneytail.toggleNegative();
                    indexes.add(poneytail);
                    break;

                case AstNodes.ARRAY_INDEX:
                    IdfArrayNode ArrayNode = new IdfArrayNode(child);
                    ArrayNode.setName(indexes.get(indexes.size()).toString());
                    indexes.set(indexes.size(), ArrayNode);
                    break;

                case AstNodes.CSTE_B:
                    throw new InvalidArrayIndexException(
                            currentNode,
                            String.valueOf(new ConstantBooleanNode(child).value));

                case AstNodes.CSTE_S:
                    throw new InvalidArrayIndexException(
                            currentNode,
                            new ConstantStringNode(child).value);

                default:
                    indexes.add(new IdfNode(child));
            }
        }
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        StringBuilder sb = new StringBuilder();

        sb.append(prefix)
                .append(nodeName);

        for (BaseNode node : indexes) {
            sb.append("[")
                    .append(node.generateCode(prefix))
                    .append("]");
        }

        return sb.toString();
    }

    /**
     *
     */
    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }
}

package ast.factory;

import ast.exception.AstBaseException;
import ast.exception.root.BadChildrenCountException;
import ast.exception.root.BadOperationNameException;
import ast.node.*;
import org.antlr.runtime.tree.Tree;
import utils.AstNodes;

public class OperationNodeFactory {
    public static OperationNode createOperationNode(Tree currentNode) throws AstBaseException {
        String nodeName = currentNode.toString();

        OperationNode operationNode = null;

        switch (nodeName) {
            case AstNodes.DIV_NODE:
                operationNode = new DivNode(currentNode);
                break;
            case AstNodes.EQ_NODE:
                operationNode = new EqNode(currentNode);
                break;
            case AstNodes.GEQ_NODE:
                operationNode = new GeqNode(currentNode);
                break;
            case AstNodes.GT_NODE:
                operationNode = new GtNode(currentNode);
                break;
            case AstNodes.LEQ_NODE:
                operationNode = new LeqNode(currentNode);
                break;
            case AstNodes.LT_NODE:
                operationNode = new LtNode(currentNode);
                break;
            case AstNodes.MINUS_NODE:
                operationNode = new MinusNode(currentNode);
                break;
            case AstNodes.MULT_NODE:
                operationNode = new MultNode(currentNode);
                break;
            case AstNodes.NEQ_NODE:
                operationNode = new NeqNode(currentNode);
                break;
            case AstNodes.PLUS_NODE:
                operationNode = new PlusNode(currentNode);
                break;
            case AstNodes.POW_NODE:
                operationNode = new PowNode(currentNode);
                break;
            case AstNodes.AND_NODE:
                operationNode = new AndNode(currentNode);
                break;
            default:
                throw new BadOperationNameException(nodeName);
        }

        return operationNode;
    }
}
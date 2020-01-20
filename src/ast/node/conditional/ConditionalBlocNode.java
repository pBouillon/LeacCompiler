package ast.node.conditional;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.exception.semantic.TypeMismatchException;
import ast.factory.OperationNodeFactory;
import ast.node.BaseNode;
import ast.node.constant.ConstantBooleanNode;
import ast.node.operation.*;
import org.antlr.runtime.tree.Tree;
import utils.GrammarConstants;

public class ConditionalBlocNode extends BaseNode {

    private ConditionNode operationNode;
    private ConditionFalseInstrBlocNode conditionFalseInstrBlocNode;
    private ConditionTrueInstrBlocNode conditionTrueInstrBlocNode;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public ConditionalBlocNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        int allowedChildrenAmount = 3;

        if (children.size() > allowedChildrenAmount) {
            throw new BadChildrenCountException(allowedChildrenAmount, children.size());
        }
    }

    @Override
    protected void exitNode() throws AstBaseException {
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        int i = 0;

        operationNode = new ConditionNode(children.get(i++));
        conditionFalseInstrBlocNode = new ConditionFalseInstrBlocNode(children.get(i++));
        conditionTrueInstrBlocNode = new ConditionTrueInstrBlocNode(children.get(i));
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        StringBuilder sb = new StringBuilder(prefix)
                .append("if (")
                .append(operationNode.generateCode(prefix))
                .append(")\n")
                .append(prefix).append("{\n")
                .append(conditionTrueInstrBlocNode.generateCode(prefix + GrammarConstants.INDENTATION))
                .append(prefix).append("}\n");

        if (conditionFalseInstrBlocNode.getChildren().size() != 0) {
            sb.append(prefix).append("else\n")
                    .append(prefix).append("{\n")
                    .append(conditionFalseInstrBlocNode.generateCode(prefix + GrammarConstants.INDENTATION))
                    .append(prefix).append("}\n");
        }

        return sb.toString();
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
    }

    @Override
    protected void performSemanticControls() throws AstBaseException {
    }
}

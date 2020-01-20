package ast.node.conditional;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.exception.semantic.TypeMismatchException;
import ast.exception.semantic.UnknownSymbolException;
import ast.node.BaseNode;
import ast.node.constant.ConstantBooleanNode;
import ast.node.operation.*;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import symbolTable.symbol.Symbol;
import symbolTable.symbol.SymbolType;
import utils.AstNodes;

public class ConditionNode extends BaseNode {

    private BaseNode conditionNode;

    public ConditionNode(Tree child) throws AstBaseException {
        super(child);
    }

    /**
     *
     */
    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        int allowedChildrenAmount = 1;

        if (children.size() != allowedChildrenAmount) {
            throw new BadChildrenCountException(allowedChildrenAmount, children.size());
        }
    }

    /**
     *
     */
    @Override
    protected void exitNode() throws AstBaseException {
    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
        Tree condition = children.get(0);

        if (condition.toString().equalsIgnoreCase(AstNodes.CSTE_B)) {
            conditionNode = new ConstantBooleanNode(condition);
        }
        else if (condition.toString().equalsIgnoreCase(AstNodes.AND_NODE)) {
            conditionNode = new AndNode(condition);
        }
        else if (condition.toString().equalsIgnoreCase(AstNodes.OR_NODE)) {
            conditionNode = new OrNode(condition);
        }
        else if (condition.toString().equalsIgnoreCase(AstNodes.LEQ_NODE)) {
            conditionNode = new LeqNode(condition);
        }
        else if (condition.toString().equalsIgnoreCase(AstNodes.LT_NODE)) {
            conditionNode = new LtNode(condition);
        }
        else if (condition.toString().equalsIgnoreCase(AstNodes.GEQ_NODE)) {
            conditionNode = new GeqNode(condition);
        }
        else if (condition.toString().equalsIgnoreCase(AstNodes.GT_NODE)) {
            conditionNode = new GtNode(condition);
        }
        else if (condition.toString().equalsIgnoreCase(AstNodes.EQ_NODE)) {
            conditionNode = new EqNode(condition);
        }
        else if (condition.toString().equalsIgnoreCase(AstNodes.NEQ_NODE)) {
            conditionNode = new NeqNode(condition);
        }
        else {
            // provided node may be an IDF
            Symbol idf = SymbolTableProvider.getCurrent().getSymbol(condition.toString());

            if (idf == null) {
                throw new UnknownSymbolException(condition.toString(), condition);
            }

            if (idf.getType() != SymbolType.BOOLEAN) {
                throw new TypeMismatchException(SymbolType.BOOLEAN.toString(), idf.getType().toString(), condition);
            }

        }
    }

    /**
     *
     */
    @Override
    protected void extractIdfs() throws AstBaseException {
    }

    /**
     * @param prefix
     */
    @Override
    public String generateCode(String prefix) throws AstBaseException {
        return conditionNode.generateCode(prefix);
    }

    /**
     *
     */
    @Override
    protected void fillSymbolTable() throws AstBaseException {
    }

    /**
     *
     */
    @Override
    protected void performSemanticControls() throws AstBaseException {
    }
}

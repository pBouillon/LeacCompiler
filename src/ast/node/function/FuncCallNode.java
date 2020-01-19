package ast.node.function;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTable;
import symbolTable.SymbolTableProvider;
import symbolTable.symbol.Symbol;
import symbolTable.symbol.SymbolType;
import utils.AstNodes;

public class FuncCallNode extends BaseNode {

    private String functionName;

    public FuncCallNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.FUNC_CALL)) {
            throw new BadNodeNameException(AstNodes.FUNC_CALL, nodeName);
        }
    }

    /**
     *
     */
    @Override
    protected void extractIdfs() throws AstBaseException {
        functionName = children.get(0).toString();
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
    protected void checkChildrenAmount() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
        // TODO
    }

    /**
     *
     */
    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    @Override
    protected void performSemanticControls() throws AstBaseException {
        if (!SymbolTableProvider.getCurrent().isSymbolRegistered(functionName)) {
            // TODO: throw ex
        }

        Symbol registeredSymbol = SymbolTableProvider.getCurrent().getSymbol(functionName);

        if (registeredSymbol.getType() != SymbolType.FUNCTION_BOOLEAN
            || registeredSymbol.getType() != SymbolType.FUNCTION_VOID
            || registeredSymbol.getType() != SymbolType.FUNCTION_STRING
            || registeredSymbol.getType() != SymbolType.FUNCTION_INT) {
            // TODO: throw ex
        }
    }
}

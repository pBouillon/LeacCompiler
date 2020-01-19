package ast.node.function;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.exception.common.BadNodeNameException;
import ast.exception.semantic.SymbolAlreadyDefinedException;
import ast.node.BaseNode;
import ast.node.InstrBlocNode;
import ast.node.idf.VarDeclListNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import symbolTable.symbol.Symbol;
import symbolTable.symbol.SymbolType;
import utils.AstNodes;

public class FuncDeclNode extends BaseNode {

    private String functionName;

    private String functionType;

    private ParamListNode paramListNode;

    private VarDeclListNode varDeclListNode;

    private InstrBlocNode instrBlocNode;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    FuncDeclNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.FUNC_DECL)) {
            throw new BadNodeNameException(AstNodes.FUNC_DECL, nodeName);
        }
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
        functionName = children.get(0).toString();
        functionType = children.get(2).toString();

        if (SymbolTableProvider.getCurrent().isSymbolRegistered(functionName)) {
            throw new SymbolAlreadyDefinedException(
                    SymbolTableProvider.getCurrent().getSymbol(functionName));
        }
    }

    @Override
    protected void exitNode() throws AstBaseException {
        SymbolTableProvider.unwrap();
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        int expectedChildren = 5;

        if (children.size() != expectedChildren) {
            throw new BadChildrenCountException(expectedChildren, children.size());
        }
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        int paramListIndex = 1;
        int varDeclIndex = 3;
        int instrBlocIndex = 4;

        paramListNode = new ParamListNode(children.get(paramListIndex));
        varDeclListNode = new VarDeclListNode(children.get(varDeclIndex));
        instrBlocNode = new InstrBlocNode(children.get(instrBlocIndex));
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
        SymbolType symbolType = SymbolType.fromString(functionType);

        if (symbolType == null) {
            // TODO: throw ex
        }

        if (symbolType == SymbolType.BOOLEAN) {
            symbolType = SymbolType.FUNCTION_BOOLEAN;
        } else if (symbolType == SymbolType.INT) {
            symbolType = SymbolType.FUNCTION_INT;
        } else if (symbolType == SymbolType.STRING) {
            symbolType = SymbolType.FUNCTION_STRING;
        } else if (symbolType == SymbolType.VOID) {
            symbolType = SymbolType.FUNCTION_VOID;
        } else {
            // TODO: throw ex
        }

        SymbolTableProvider.getCurrent().registerSymbol(
                new Symbol(functionName, symbolType, currentNode));

        SymbolTableProvider.nest();
    }

    @Override
    protected void performSemanticControls() throws AstBaseException {
    }
}

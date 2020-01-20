package ast.node.function;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.exception.common.BadNodeNameException;
import ast.exception.semantic.SymbolAlreadyDefinedException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import utils.AstNodes;

public class ParamNode extends BaseNode {

    private String paramName;
    private String paramType;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    ParamNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.PARAM)) {
            throw new BadNodeNameException(AstNodes.PARAM, nodeName);
        }
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        int childrenNumber = 2;

        if (children.size() != childrenNumber) {
            throw new BadChildrenCountException(childrenNumber, children.size());
        }
    }

    @Override
    protected void exitNode() throws AstBaseException {

    }

    @Override
    protected void extractChildren() throws AstBaseException {
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
        paramName = children.get(0).toString();
        paramType = children.get(1).toString();
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        return paramType + " " + paramName;
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    @Override
    protected void performSemanticControls() throws AstBaseException {
        if (SymbolTableProvider.getCurrent().isSymbolRegistered(paramName)) {
            throw new SymbolAlreadyDefinedException(
                    SymbolTableProvider.getCurrent().getSymbol(paramName));
        }
    }
}

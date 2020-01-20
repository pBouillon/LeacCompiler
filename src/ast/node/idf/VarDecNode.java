package ast.node.idf;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.exception.semantic.TypeMismatchException;
import ast.exception.semantic.UnknownSymbolException;
import ast.node.BaseNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import symbolTable.symbol.Symbol;
import symbolTable.symbol.SymbolType;
import utils.AstNodes;

import java.util.ArrayList;

public class VarDecNode extends BaseNode {

    private ArrayList<String> idfs;
    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected VarDecNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.VAR_DECL)) {
            throw new BadNodeNameException(AstNodes.VAR_DECL, nodeName);
        }
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
    protected void exitNode() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractIdfs() throws AstBaseException {
        idfs = new ArrayList<>();
        for(int i = 1; i< children.size() ; i++ ) {
            idfs.add(children.get(i).toString());
        }
    }

    /**
     * @param prefix
     */
    @Override
    public String generateCode(String prefix) throws AstBaseException {
        StringBuilder sb = new StringBuilder();

        for (String idf : idfs) {
            sb.append(prefix)
                    .append(idf);
            if (!idf.equals(idfs.get(idfs.size()-1)))
                sb.append(',');
            else
                sb.append(";\n");
        }
        return children.get(0).toString() + ' ' + sb.toString();
    }

    /**
     *
     */
    @Override
    protected void fillSymbolTable() throws AstBaseException {
        for (String idf: idfs) {
            SymbolType symbolType = SymbolType.fromString(children.get(0).toString());

            if (symbolType == null) {
                throw new UnknownSymbolException(symbolType.toString(), currentNode);
            }


            switch (symbolType) {
                case BOOLEAN:
                    symbolType = SymbolType.BOOLEAN;
                    break;
                case INT:
                    symbolType = SymbolType.INT;
                    break;
                case STRING:
                    symbolType = SymbolType.STRING;
                    break;
                case TARRAY:
                    symbolType = SymbolType.TARRAY;
                    break;
                default:
                    throw new TypeMismatchException(currentNode);
            }
            SymbolTableProvider.getCurrent().registerSymbol(
                    new Symbol(idf, symbolType, currentNode));
        }
    }

    /**
     *
     */
    @Override
    protected void performSemanticControls() throws AstBaseException {

    }
}

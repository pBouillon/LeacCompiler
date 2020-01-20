package ast.node.function;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.exception.semantic.TypeMismatchException;
import ast.exception.semantic.UnknownSymbolException;
import ast.factory.OperationNodeFactory;
import ast.node.BaseNode;
import ast.node.constant.ConstantBooleanNode;
import ast.node.constant.ConstantNumericNode;
import ast.node.constant.ConstantStringNode;
import ast.node.idf.IdfArrayNode;
import ast.node.idf.IdfNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import symbolTable.symbol.Symbol;
import symbolTable.symbol.SymbolType;
import utils.AstNodes;

import java.util.ArrayList;

public class FuncCallNode extends BaseNode {

    private String functionName;
    private ArrayList<String> idfs;
    private ArrayList<BaseNode> items;

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
        idfs = new ArrayList<>();
        items = new ArrayList<>();

        functionName = children.get(0).toString();

        for(int i = 1; i < children.size() ; i++) {
            switch(children.get(i).toString()) {
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
                case AstNodes.ARRAY_INDEX:
                case AstNodes.CSTE_N:
                case AstNodes.CSTE_B:
                case AstNodes.CSTE_S:
                    break;
                default:
                    idfs.add(children.get(i).toString());
            }
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
    protected void checkChildrenAmount() throws AstBaseException {

    }

    /**
     *
     */
    @Override
    protected void extractChildren() throws AstBaseException {
        // Assign each child
        for(int i = 1 ; i < children.size() ; i++) {
            switch(children.get(i).toString()) {
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
                    items.add(OperationNodeFactory.createOperationNode(children.get(i)));
                    break;
                case AstNodes.ARRAY_INDEX:
                    IdfArrayNode ArrayNode = new IdfArrayNode(children.get(i));
                    ArrayNode.setName(items.get(items.size()-1).toString());
                    items.set(items.size()-1, ArrayNode);
                    break;
                case AstNodes.CSTE_N:
                    items.add(new ConstantNumericNode(children.get(i)));
                    break;
                case AstNodes.CSTE_B:
                    items.add(new ConstantBooleanNode(children.get(i)));
                    break;
                case AstNodes.CSTE_S:
                    items.add(new ConstantStringNode(children.get(i)));
                    break;
                default:
                    items.add(new IdfNode(children.get(i)));
            }
        }
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        StringBuilder sb = new StringBuilder();

        sb.append(prefix)
            .append(functionName)
            .append("(");

        for (BaseNode param : items) {
            sb.append(param.generateCode(prefix));
            if (!param.equals(items.get(items.size()-1)))
                    sb.append(", ");
        }
        
        return sb.append(");").toString();
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
            throw new UnknownSymbolException(functionName, currentNode);
        }

        Symbol registeredSymbol = SymbolTableProvider.getCurrent().getSymbol(functionName);

        if (registeredSymbol.getType() != SymbolType.FUNCTION_BOOLEAN
            && registeredSymbol.getType() != SymbolType.FUNCTION_VOID
            && registeredSymbol.getType() != SymbolType.FUNCTION_STRING
            && registeredSymbol.getType() != SymbolType.FUNCTION_INT) {
            throw new TypeMismatchException("FUNCTION", registeredSymbol.getType().toString(), currentNode);
        }

        for (String idf : idfs) {
            if (!SymbolTableProvider.getCurrent().isSymbolRegistered((idf))) {
                throw new UnknownSymbolException(idf, currentNode);
            }
            // Todo : check if idfs are the same type as function decl
        }

    }
}

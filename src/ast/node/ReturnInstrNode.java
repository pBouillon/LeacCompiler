package ast.node;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.exception.semantic.UnknownSymbolException;
import ast.factory.OperationNodeFactory;
import ast.node.constant.ConstantBooleanNode;
import ast.node.constant.ConstantNumericNode;
import ast.node.constant.ConstantStringNode;
import ast.node.function.FuncCallNode;
import ast.node.idf.IdfArrayNode;
import ast.node.idf.IdfNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import utils.AstNodes;

import java.util.ArrayList;

public class ReturnInstrNode extends BaseNode {

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */

    private ArrayList<BaseNode> items;
    private ArrayList<String> idfs;

    public ReturnInstrNode(Tree child) throws AstBaseException {
        super(child);
        if (!nodeName.equals(AstNodes.RETURN_INSTR)) {
            throw new BadNodeNameException(AstNodes.RETURN_INSTR, nodeName);
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
        items = new ArrayList<>();
        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.ARRAY_INDEX:
                    IdfArrayNode ArrayNode = new IdfArrayNode(child);
                    ArrayNode.setName(items.get(items.size()-1).toString());
                    items.set(items.size()-1, ArrayNode);
                    break;
                case AstNodes.FUNC_CALL:
                    items.add(new FuncCallNode(child));
                    break;
                case AstNodes.CSTE_N:
                    items.add(new ConstantNumericNode(child));
                    break;
                case AstNodes.CSTE_B:
                    items.add(new ConstantBooleanNode(child));
                    break;
                case AstNodes.CSTE_S:
                    items.add(new ConstantStringNode(child));
                    break;
                case AstNodes.PLUS_NODE:
                case AstNodes.MINUS_NODE:
                case AstNodes.MULT_NODE:
                case AstNodes.DIV_NODE:
                case AstNodes.POW_NODE:
                case AstNodes.LT_NODE:
                case AstNodes.LEQ_NODE:
                case AstNodes.GT_NODE:
                case AstNodes.GEQ_NODE:
                case AstNodes.EQ_NODE:
                case AstNodes.NEQ_NODE:
                case AstNodes.AND_NODE:
                case AstNodes.OR_NODE:
                    items.add(OperationNodeFactory.createOperationNode(child));
                default:
                    items.add(new IdfNode(child));
                    break;
            }
        }
    }

    /**
     *
     */
    @Override
    protected void extractIdfs() throws AstBaseException {
        idfs =  new ArrayList<>();
        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.ARRAY_INDEX:
                case AstNodes.FUNC_CALL:
                case AstNodes.CSTE_N:
                case AstNodes.CSTE_B:
                case AstNodes.CSTE_S:
                case AstNodes.PLUS_NODE:
                case AstNodes.MINUS_NODE:
                case AstNodes.MULT_NODE:
                case AstNodes.DIV_NODE:
                case AstNodes.POW_NODE:
                case AstNodes.LT_NODE:
                case AstNodes.LEQ_NODE:
                case AstNodes.GT_NODE:
                case AstNodes.GEQ_NODE:
                case AstNodes.EQ_NODE:
                case AstNodes.NEQ_NODE:
                case AstNodes.AND_NODE:
                case AstNodes.OR_NODE:
                    break;
                default:
                    idfs.add(child.toString());
            }
        }
    }

    /**
     * @param prefix
     */
    @Override
    public String generateCode(String prefix) throws AstBaseException {
        // TODO: idf code gen
        StringBuilder sb = new StringBuilder();

        for( BaseNode item : items) {
            sb.append(item.generateCode(prefix));
        }
        return prefix + "return " +sb.toString()+";\n";
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
        for (String idf : idfs) {
            if (!SymbolTableProvider.getCurrent().isSymbolRegistered(idf)) {
                throw new UnknownSymbolException(idf, currentNode);
            }
        }

    }
}

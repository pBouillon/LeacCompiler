package ast.node;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.exception.common.BadNodeNameException;
import ast.exception.semantic.TypeMismatchException;
import ast.exception.semantic.UnknownSymbolException;
import ast.node.constant.ConstantBooleanNode;
import ast.node.constant.ConstantNode;
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

public class WriteInstrNode extends BaseNode {

    private ArrayList<BaseNode> items;
    private ArrayList<String> idfs;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    public WriteInstrNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.WRITE_INSTR)) {
            throw new BadNodeNameException(AstNodes.WRITE_INSTR, nodeName);
        }
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {

    }

    @Override
    protected void exitNode() throws AstBaseException {

    }

    @Override
    protected void extractChildren() throws AstBaseException {
        items = new ArrayList<>();

        // Assign each child
        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.ARRAY_INDEX:
                    IdfArrayNode ArrayNode = new IdfArrayNode(child);
                    ArrayNode.setName(items.get(items.size()-1).toString());
                    items.set(items.size()-1, ArrayNode);
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
                default:
                    items.add(new IdfNode(child));
                    break;
            }
        }
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
        idfs =  new ArrayList<>();
        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.ARRAY_INDEX:
                case AstNodes.CSTE_N:
                case AstNodes.CSTE_B:
                case AstNodes.CSTE_S:
                    break;
                default:
                    idfs.add(child.toString());
            }
        }
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
        // TODO: idf code gen
        return prefix + "printf(\"\", " + "TODO" + ");\n";
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {

    }

    @Override
    protected void performSemanticControls() throws AstBaseException {
        for (String idf : idfs) {
            if (!SymbolTableProvider.getCurrent().isSymbolRegistered(idf)) {
                throw new UnknownSymbolException(idf, currentNode);
            }
        }
    }
}

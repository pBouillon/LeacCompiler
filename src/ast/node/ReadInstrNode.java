package ast.node;

import ast.exception.AstBaseException;
import ast.exception.common.BadNodeNameException;
import ast.exception.semantic.UnknownSymbolException;
import ast.node.idf.IdfArrayNode;
import ast.node.idf.IdfNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import utils.AstNodes;

import java.util.ArrayList;

public class ReadInstrNode extends BaseNode {

    private ArrayList<BaseNode> items;
    private ArrayList<String> idfs;

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected ReadInstrNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.READ_INSTR)) {
            throw new BadNodeNameException(AstNodes.READ_INSTR, nodeName);
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
                    ArrayNode.setName(items.get(items.size() - 1).toString());
                    items.set(items.size() - 1, ArrayNode);
                    break;
                default:
                    items.add(new IdfNode(child));
                    break;
            }
        }
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
        idfs = new ArrayList<>();

        for (Tree child : children) {
            switch (child.toString()) {
                case AstNodes.ARRAY_INDEX:
                    break;
                default:
                    idfs.add(child.toString());
            }
        }
    }

    @Override
    public String generateCode(String prefix) throws AstBaseException {
            // TODO: idf code gen
            return prefix + "scanf(\"\", " + "TODO" + ")";
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

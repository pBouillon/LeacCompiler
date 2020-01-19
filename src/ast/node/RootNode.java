package ast.node;

import ast.exception.AstBaseException;
import ast.exception.common.BadChildrenCountException;
import ast.exception.common.BadNodeNameException;
import ast.node.function.FuncDeclListNode;
import ast.node.idf.VarDeclListNode;
import org.antlr.runtime.tree.Tree;
import symbolTable.SymbolTableProvider;
import symbolTable.symbol.Symbol;
import symbolTable.symbol.SymbolType;
import utils.AstNodes;

/**
 * ast.node.RootNode is the root node of the built AST
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class RootNode extends BaseNode {

    /**
     * @see FuncDeclListNode
     */
    private FuncDeclListNode funcDeclListNode;

    /**
     * @see InstrBlocNode
     */
    private InstrBlocNode instrBlocNode;

    /**
     * Main program's name
     */
    private String programName;

    /**
     * @see VarDeclListNode
     */
    private VarDeclListNode varDeclListNode;

    /**
     * Default constructor for the root node
     *
     * @param _currentNode ANTLR raw AST
     */
    public RootNode(Tree _currentNode) throws AstBaseException {
        super(_currentNode);

        if (!nodeName.equals(AstNodes.ROOT)) {
            throw new BadNodeNameException(AstNodes.ROOT, nodeName);
        }
    }

    @Override
    protected void extractIdfs() throws AstBaseException {
        programName = children.get(0).toString();
        children.remove(0);
    }

    @Override
    protected void exitNode() throws AstBaseException {
        SymbolTableProvider.unwrap();
    }

    @Override
    protected void checkChildrenAmount() throws AstBaseException {
        // Assert allowed children
        int childrenNumber = 4;

        if (children.size() != childrenNumber) {
            throw new BadChildrenCountException(childrenNumber,children.size());
        }
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        // Assign each child
        for (Tree child: children) {
            switch (child.toString()) {
                case AstNodes.VAR_DECL_LIST:
                    varDeclListNode = new VarDeclListNode(child);
                    break;

                case AstNodes.FUNC_DECL_LIST:
                    funcDeclListNode = new FuncDeclListNode(child);
                    break;

                case AstNodes.INSTR_BLOC:
                    instrBlocNode = new InstrBlocNode(child);
                    break;

                default:
                    // TODO: throw exp
            }
        }
    }

    @Override
    protected void fillSymbolTable() throws AstBaseException {
        Symbol programEntry = new Symbol(programName, SymbolType.PROGRAM, currentNode);

        SymbolTableProvider
                .getCurrent()
                .registerSymbol(programEntry);
    }

}

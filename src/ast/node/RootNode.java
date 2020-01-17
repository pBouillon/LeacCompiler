package ast.node;

import ast.exception.AstBaseException;
import ast.exception.root.BadChildrenCountException;
import ast.exception.root.BadNodeNameException;
import org.antlr.runtime.tree.Tree;
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
            throw new BadNodeNameException("Node name should be " + AstNodes.ROOT + " but is " + nodeName);
        }
    }

    @Override
    protected void extractChildren() throws AstBaseException {
        // Assert allowed children
        int childrenNumber = 4;

        if (children.size() != childrenNumber) {
            throw new BadChildrenCountException(
                    "Bad children amount, expected " + childrenNumber + " but found " + children.size());
        }

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
                    programName = child.toString();
            }
        }
    }

}

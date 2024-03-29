package ast.node;

import ast.exception.AstBaseException;
import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;

/**
 * ast.node.BaseNode represent a node of our custom AST
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public abstract class BaseNode {

    /**
     * Node's children
     */
    protected ArrayList<Tree> children;

    /**
     * Node's name
     */
    protected String nodeName;

    /**
     * ANTLR raw AST
     */
    protected Tree currentNode;

    public ArrayList<Tree> getChildren() {
        return children;
    }

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    protected BaseNode(Tree _currentNode) throws AstBaseException {
        currentNode = _currentNode;

        // Fetch node's name
        nodeName = currentNode.toString();

        // Gather node's child
        children = new ArrayList<>();

        for (int i = 0; i < currentNode.getChildCount(); ++i) {
            children.add(currentNode.getChild(i));
        }

        // Ensure children amount
        checkChildrenAmount();

        // Extract relevant idfs
        extractIdfs();

        // Fill the symbol table
        fillSymbolTable();

        // Extract all its children
        extractChildren();

        // Perform controls
        performSemanticControls();

        // Exit this node
        exitNode();
    }

    /**
     *
     */
    abstract protected void checkChildrenAmount() throws AstBaseException;

    /**
     *
     */
    abstract protected void exitNode() throws AstBaseException;

    /**
     *
     */
    abstract protected void extractChildren() throws AstBaseException;

    /**
     *
     */
    abstract protected void extractIdfs() throws AstBaseException;

    /**
     *
     */
    abstract public String generateCode(String prefix) throws AstBaseException;

    /**
     *
     */
    abstract protected void fillSymbolTable() throws AstBaseException;

    /**
     *
     */
    abstract protected void performSemanticControls() throws AstBaseException;

    /**
     * Default toString method
     *
     * @return the current node's name
     */
    @Override
    public String toString() {
        return nodeName;
    }

}

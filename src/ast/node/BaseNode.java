package ast.node;

import ast.exception.AstBaseException;
import ast.exception.root.BadChildrenCountException;
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
abstract class BaseNode {

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

    /**
     * Default constructor to ensure the usage of the ANTLR raw AST
     *
     * @param _currentNode ANTLR raw AST
     */
    BaseNode(Tree _currentNode) throws AstBaseException {
        currentNode = _currentNode;

        // Fetch node's name
        nodeName = currentNode.toString();

        // Gather node's child
        children = new ArrayList<>();

        for (int i = 0; i < currentNode.getChildCount(); ++i) {
            children.add(currentNode.getChild(i));
        }

        // Extract all its childs
        extractChildren();
    }

    /**
     *
     */
    abstract protected void extractChildren() throws AstBaseException;

    /**
     * Default toString method
     *
     * @return the current node's name
     */
    @Override
    public String toString() {
        return nodeName ;
    }

    /**
     * Access to all this node's children
     *
     * @return an ArrayList of those children
     */
    public ArrayList<Tree> getChildren() {
        return children;
    }

}
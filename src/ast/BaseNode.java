package ast;

import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;

/**
 * ast.BaseNode represent a node of our custom AST
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
abstract class BaseNode {

    /**
     * Node's childs
     */
    protected ArrayList<Tree> childs;

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
    BaseNode(Tree _currentNode) {
        currentNode = _currentNode;

        // Fetch node's name
        nodeName = currentNode.toString();

        // Gather node's child
        childs = new ArrayList<>();

        for (int i = 0; i < currentNode.getChildCount(); ++i) {
            childs.add(currentNode.getChild(i));
        }
    }

    /**
     * Default toString method
     *
     * @return the current node's name
     */
    @Override
    public String toString() {
        return nodeName ;
    }
    
}

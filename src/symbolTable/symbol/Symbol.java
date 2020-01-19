package symbolTable.symbol;

import org.antlr.runtime.tree.Tree;

public class Symbol {

    private int columnNumber;

    private String idf;

    private boolean isInitialized;

    private int lineNumber;

    private SymbolTypes type;

    private Object value;

    public Symbol(String _idf, SymbolTypes _type, Object _value, boolean _isInitialized, Tree node) {
        idf = _idf;
        type = _type;
        value = _value;
        isInitialized = _isInitialized;

        extractSymbolMetaData(node);
    }

    public Symbol(String _idf, SymbolTypes _type, Tree node) {
        idf = _idf;
        type = _type;
        value = null;
        isInitialized = false;

        extractSymbolMetaData(node);
    }

    private void extractSymbolMetaData(Tree node) {
        lineNumber = node.getLine();
        columnNumber = node.getText().indexOf(idf);
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public String getIdf() {
        return idf;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public SymbolTypes getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}

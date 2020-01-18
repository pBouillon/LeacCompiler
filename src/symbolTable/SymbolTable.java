package symbolTable;

import symbolTable.symbol.Symbol;

import java.util.HashMap;

public class SymbolTable implements ISymbolTable {

    private SymbolTable origin;

    private HashMap<String, Symbol> symbols;

    public SymbolTable(SymbolTable _origin) {
        origin = _origin;

        symbols = new HashMap<>();
    }

    public SymbolTable getOrigin() {
        return origin;
    }

    public boolean isSymbolRegistered(String idf) {
        return symbols.containsKey(idf);
    }

    public Symbol getSymbol(String idf) {
        return symbols.get(idf);
    }

    public void registerSymbol(Symbol symbol) {
        symbols.put(symbol.getIdf(), symbol);
    }
}

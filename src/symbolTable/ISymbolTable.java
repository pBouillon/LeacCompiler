package symbolTable;

import symbolTable.symbol.Symbol;

public interface ISymbolTable {

    SymbolTable getOrigin();

    boolean isSymbolRegistered(String idf);

    Symbol getSymbol(String idf);

    void registerSymbol(Symbol symbol);

}

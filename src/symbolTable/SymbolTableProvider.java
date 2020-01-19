package symbolTable;

public class SymbolTableProvider {

    private static SymbolTable currentSymbolTable;

    private static SymbolTable rootSymbolTable;

    public static void initialise() {
        rootSymbolTable = new SymbolTable(null);
        currentSymbolTable = rootSymbolTable;
    }

    public static void addNested() {
        currentSymbolTable = new SymbolTable(currentSymbolTable);
    }

    public static void unwrap() {
        currentSymbolTable = currentSymbolTable.getOrigin();
    }

    public static SymbolTable getRoot() {
        return rootSymbolTable;
    }

    public static SymbolTable getCurrent() {
        return currentSymbolTable;
    }
}

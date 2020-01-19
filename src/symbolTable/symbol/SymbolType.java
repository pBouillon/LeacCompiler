package symbolTable.symbol;

public enum SymbolType {
    BOOLEAN,
    INT,
    STRING,
    VOID,

    PARAMETER,
    FUNCTION_BOOLEAN,
    FUNCTION_INT,
    FUNCTION_STRING,
    FUNCTION_VOID,
    PROGRAM;

    public static SymbolType fromString(String toConvert) {
        for (SymbolType symbolType : SymbolType.values()) {
            if (symbolType.toString().equalsIgnoreCase(toConvert)) {
                return symbolType;
            }
        }
        return null;
    }
}

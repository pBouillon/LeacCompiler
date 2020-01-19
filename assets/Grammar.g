grammar Grammar;

/*
 * --------------------
 ANTLR options
 --------------------
 */

options {
    // Grammar LL(1)
    k = 1;

    // Configure generation AST as output
    output = AST;
}

tokens {
    AND;
    ARRAY_ACCESS;
	ARRAY_INDEX;
    CONDITION;
    CONDITIONNAL_BLOC;
    CONDITION_FALSE_INSTR_BLOC;
    CONDITION_TRUE_INSTR_BLOC;
    CSTE_B;
    CSTE_N;
    CSTE_S;
    DIV;
    ELSE_BLOC;
    EQ;
    FUNC_CALL;
    FUNC_DECL;
    FUNC_DECL_LIST;
    GEQ;
    GT;
    INDEX;
    INSTR_BLOC;
    LEQ;
    LMEMBER;
    LOOP;
    LT;
    MINUS;
    MULT;
    NEQ;
    NOT;
    OR;
    PARAM;
    PARAM_LIST;
    PLUS;
    POW;
    RANGE;
    READ_INSTR;
    REF_PARAM;
    RETURN_INSTR;
    RMEMBER;
    ROOT;
	TARRAY;
    UMINUS;
    VALUE;
    VAR_AFFECT;
    VAR_DECL;
    VAR_DECL_LIST;
    WRITE_INSTR;
}

/*
 * --------------------
 Headers
 --------------------
 */

@lexer::header {
    package antlr.assets;
}

@parser::header {
    package antlr.assets;

    import java.util.HashMap;
}

/*
 * --------------------
 Parser rules
 --------------------
 */

@members {
    /**
      * Map variable name to Integer object holding value 
      */
    HashMap<String,Integer>  memory = new HashMap<String,Integer>();
}

program
    : PROGRAM IDF vardeclist funcdeclist instr WS* NEWLINE* -> ^(ROOT IDF ^(VAR_DECL_LIST vardeclist?) ^(FUNC_DECL_LIST funcdeclist?) ^(INSTR_BLOC instr?))
    ;

vardeclist
    : varsuitdecl* -> ^(VAR_DECL_LIST varsuitdecl*)
    ;

varsuitdecl
    : VAR identlist ':' typename ';' -> ^(VAR_DECL typename identlist)
    ;

identlist
    : IDF (','! IDF)*
    ;

typename
    : atomtype
    | arraytype
    ;

atomtype
    : VOID
    | BOOL
    | INT
    ;

arraytype
    : ARRAY '[' rangelist ']' OF atomtype -> ^(TARRAY atomtype rangelist)
    ;

rangelist
    : atom '..' atom rangelist_1 -> ^(RANGE atom atom) rangelist_1?
    ;

rangelist_1
    : ',' rangelist -> rangelist
    | ->
    ;

funcdeclist
    : (funcdec)*
    ;

funcdec
	:	FUNCTION IDF '(' arglist ')' ':' atomtype vardeclist '{' end_sequence -> ^(FUNC_DECL IDF arglist? atomtype vardeclist? ^(INSTR_BLOC end_sequence?))
	;

arglist
    : arg (',' arg)* -> ^(PARAM_LIST arg*)
    | -> 
    ;

arg
    : IDF ':' typename -> ^(PARAM IDF typename)
    | REF IDF ':' typename -> ^(REF_PARAM IDF typename)
    ;

instr
    : IF expr THEN onTrue=instr (options {greedy = true; }: ELSE onFalse=instr)*  -> ^(CONDITIONNAL_BLOC ^(CONDITION expr) ^(CONDITION_TRUE_INSTR_BLOC $onTrue) ^(CONDITION_FALSE_INSTR_BLOC $onFalse?)?)
    | WHILE expr DO instr -> ^(LOOP ^(CONDITION expr) ^(INSTR_BLOC instr))
    | IDF ( lvalue_1 '=' expr -> ^(VAR_AFFECT ^(LMEMBER IDF lvalue_1?) ^(RMEMBER expr))
    	| '(' param -> ^(FUNC_CALL IDF param*)
    )
    | RETURN expr? -> ^(RETURN_INSTR expr?)
    | '{' end_sequence -> end_sequence?
    | READ lvalue -> ^(READ_INSTR ^(VALUE lvalue))
    | WRITE write_param -> ^(WRITE_INSTR ^(VALUE write_param))
    ;

param
    : ')' ->
    | exprList ')' -> exprList
    ;

write_param
    : lvalue 
    | cste
    ;

end_sequence
    : sequence '}' -> sequence
    | '}' -> 
    ;

sequence
    : instr sequence_1 -> instr sequence_1?
    ;

sequence_1
    : ';' sequence_2 -> sequence_2
    | ->
    ;

sequence_2
	: sequence 
    |
    ;

lvalue
    : IDF lvalue_1
    ;

lvalue_1
    : '[' exprList ']' -> ^(ARRAY_INDEX exprList)
    | ->
    ;

exprList
    : expr exprList_1
    ;

exprList_1
    : ',' exprList -> exprList
    | ->
    ;

expr
    : expr_compare (andOrOps^ expr_compare)*
    ;
    
expr_compare
    : expr_plusmin (compareOps^ expr_plusmin)*
    ;
    
expr_plusmin
    : expr_muldiv (plusminOps^ expr_muldiv)*
    ;

expr_muldiv
    : expr_power (muldivOps^ expr_power)*
    ;

expr_power
    : expr_base (powerOps^ expr_base)*
    ;

expr_base
    : '(' expr_compare ')' -> expr_compare
    | expr_final
    ;
    
expr_final
    : cste 
    | opun expr_final -> ^(opun expr_final)
    | i=IDF ( '(' expr_2 -> ^(FUNC_CALL $i expr_2)
          | '[' exprList ']' -> $i ^(INDEX exprList)
          | -> $i
          )
    ;

expr_2
    : exprList ')' -> exprList
    | ')' ->
    ;

compareOps
    : '<' -> LT
    | '<=' -> LEQ
    | '>' -> GT
    | '>=' -> GEQ
    | '==' -> EQ
    | '!='-> NEQ
    ;

muldivOps
    : '*' -> MULT
    | '/' -> DIV
    ;

powerOps 
    : '^' -> POW
    ;

andOrOps
    : 'and' -> AND
    | 'or' -> OR
    ;

plusminOps
    : '+' -> PLUS
    | '-' -> MINUS
    ;

opun
    : '-' -> UMINUS
    | 'not' -> NOT
    ;

atom
    : opun? cste
    ;

cste
	: CSTE_NUM -> ^(CSTE_N CSTE_NUM)
	| CSTE_BOOL -> ^(CSTE_B CSTE_BOOL)
	| CSTE_STR -> ^(CSTE_S CSTE_STR)
	;

/*
* --------------------
Lexer rules
--------------------
*/

// Globals
COMMENT_BLOC: '/*' (options {greedy=false;}: .)* '*/' {skip();};
NEWLINE: '\r'? '\n' {skip();};
WS: (' ' | '\t')+ {skip();};

// Keywords
ARRAY: 'array';
BOOL: 'bool';
DO: 'do';
ELSE: 'else';
FUNCTION: 'function';
IF: 'if';
INT: 'int';
OF: 'of';
PROGRAM: 'program';
READ: 'read';
REF: 'ref';
RETURN: 'return';
THEN: 'then';
VAR: 'var';
VOID: 'void';
WHILE: 'while';
WRITE: 'write';

// Lexical aspects Represent an identifier
fragment CHARACTER
    : 'a'..'z'
    | 'A'..'Z'
    ;

CSTE_BOOL
    : 'true'
    | 'false'
    ;
    
IDF
    : CHARACTER (CHARACTER | DIGIT)*
    ;

// Represent a numeric constant

fragment DIGIT
    : '0'..'9'
    ;

CSTE_NUM
    : DIGIT+
    ;

// Represent a text constant
CSTE_STR
    : DOUBLE_QUOTES_STR
    | SINGLE_QUOTE_STR
    ;

fragment DOUBLE_QUOTES_STR
    : '"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"'
    ;

fragment SINGLE_QUOTE_STR
    : '\'' (~('\'' | '\\' | '\r' | '\n') | '\\' ('\'' | '\\'))* '\''
    ;

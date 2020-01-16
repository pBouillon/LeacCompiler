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
    ARG_LIST;
    ARG;
    FUNC_DECL_LIST;
    FUNC_DECL;
    REF_ARG;
    ROOT;
    VAR_DECL_LIST;
    VAR_DECL;
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
    : PROGRAM IDF vardeclist funcdeclist instr WS* NEWLINE* -> ^(ROOT IDF vardeclist funcdeclist instr)
    ;

vardeclist
    : varsuitdecl* -> ^(VAR_DECL_LIST varsuitdecl*)
    ;

varsuitdecl
    : VAR identlist ':' typename ';' -> ^(VAR_DECL typename identlist)
    ;

identlist
    : IDF identlist_1
    ;

identlist_1
    : ',' identlist -> identlist
    |
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
    : ARRAY '[' rangelist ']' OF atomtype -> rangelist atomtype
    ;

rangelist
    : atom '..' atom rangelist_1 -> atom atom rangelist_1
    ;

rangelist_1
    : ',' rangelist -> rangelist
    |
    ;

funcdeclist
    : funcdecl funcdeclist 
    |
    ;

funcdecl
    : FUNCTION IDF '(' arglist ')' ':' atomtype vardeclist '{' end_sequence -> ^(FUNC_DECL_LIST IDF arglist atomtype vardeclist end_sequence)
    ;

arglist
	: arg (',' arg)* -> ^(ARG_LIST arg*)
	| -> 
	;

arg
    : IDF ':' typename -> ^(ARG IDF typename)
    | REF IDF ':' typename -> ^(REF_ARG IDF typename)
    ;

instr
    : IF expr THEN instr (options {greedy = true; }: ELSE instr)* 
    | WHILE expr DO instr -> expr instr
    | IDF instr_after_idf
    | RETURN ret_1
    | '{' end_sequence -> end_sequence
    | READ lvalue 
    | WRITE write_param
    ;
    
instr_after_idf
	: lvalue_1 '=' expr -> lvalue_1 expr
	| '(' param -> param*
	;

ret_1
    : expr
    | 
    ;

param
    : ')' -> 
    | exprList ')' -> exprList
    ;

write_param
    : lvalue 
    | CSTE
    ;

end_sequence
    : sequence '}' -> sequence
    | '}' ->
    ;

sequence
    : instr sequence_1
    ;

sequence_1
    : ';' sequence_2 -> sequence_2
    |
    ;

sequence_2
	: sequence 
    |
    ;

lvalue
    : IDF lvalue_1
    ;

lvalue_1
    : '[' exprList ']' -> exprList
    |
    ;

exprList
    : expr exprList_1
    ;

exprList_1
    : ',' exprList -> exprList
    |
    ;

expr
	: expr_compare (andOrOps expr_compare)*
	;
	
expr_compare
    : expr_plusmin (compareOps expr_plusmin)*
    ;
    
expr_plusmin
    : expr_muldiv (plusminOps expr_muldiv)*
    ;

expr_muldiv
    : expr_power (muldivOps expr_power)*
    ;

expr_power
	: expr_base (powerOps expr_base)*
	;

expr_base
	: '(' expr_compare ')' -> expr_compare
	| expr_final
	;
	
expr_final
    : CSTE 
    | opun expr_final
    | IDF expr_1
    ;

expr_1
    : '(' expr_2 -> expr_2
    | '[' exprList ']' -> exprList
    |
    ;

expr_2
    : exprList ')' -> exprList
    | ')' ->
    ;

compareOps
    : '<' ->
    | '<=' ->
    | '>' ->
    | '>=' ->
    | '==' ->
    | '!='->
    ;

muldivOps
    : '*' ->
    | '/' ->
    ;

powerOps 
	: '^' ->
    ;

andOrOps
    : 'and' ->
    | 'or' ->
    ;

plusminOps
    : '+' ->
    | '-' ->
    ;

opun
    : '-' ->
    | 'not' ->
    ;

atom
	: opun? CSTE
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

IDF
    : CHARACTER (CHARACTER | DIGIT)*
    ;

// Represent a constant of any kind
fragment CSTE_BOOL
    : 'true'
    | 'false'
    ;

// Represent a numeric constant
CSTE
    : CSTE_BOOL
    | CSTE_NUM 
    | CSTE_STR
    ;

fragment DIGIT
    : '0'..'9'
    ;

fragment CSTE_NUM
    : DIGIT+
    ;

// Represent a text constant
fragment CSTE_STR
    : DOUBLE_QUOTES_STR
    | SINGLE_QUOTE_STR
    ;

fragment DOUBLE_QUOTES_STR
    : '"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"'
    ;

fragment SINGLE_QUOTE_STR
    : '\'' (~('\'' | '\\' | '\r' | '\n') | '\\' ('\'' | '\\'))* '\''
    ;

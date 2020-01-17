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
	CONDITION;
	CONDITIONNAL_BLOC;
	CONDITION_TRUE_INSTR_BLOC;
	CONDITION_FALSE_INSTR_BLOC;
	ELSE_BLOC;
    FUNC_DECL_LIST;
    FUNC_DECL;
    INDEX;
    INSTR_BLOC;
    LOOP;
    PARAM;
    PARAM_LIST;
    READ_INSTR;
    REF_PARAM;
    RETURN_INSTR;
    ROOT;
    VALUE;
    VAR_DECL_LIST;
    VAR_DECL;
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
    : varsuitdecl*
    ;

varsuitdecl
    : VAR identlist ':' typename ';' -> ^(VAR_DECL typename identlist)
    ;

identlist
    : IDF (',' IDF)*
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
    : (FUNCTION IDF '(' arglist ')' ':' atomtype vardeclist '{' func_bloc -> ^(FUNC_DECL IDF arglist atomtype vardeclist ^(INSTR_BLOC func_bloc?)))*
    ;

func_bloc
	: sequence '}' -> sequence
	| '}' ->
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
    | IDF instr_after_idf
    | RETURN expr? -> ^(RETURN_INSTR expr?)
    | '{' sequence? '}' -> sequence?
    | READ lvalue -> ^(READ_INSTR ^(VALUE lvalue))
    | WRITE write_param -> ^(WRITE_INSTR ^(VALUE write_param))
    ;
    

instr_after_idf
	: lvalue '=' expr -> lvalue expr
	| '(' param -> param*
	;

param
    : ')' -> 
    | exprList ')' -> exprList
    ;

write_param
    : lvalue 
    | CSTE
    ;

sequence
    : instr sequence_1 -> instr sequence_1?
    ;

sequence_1
	: ';' sequence -> sequence
	| ->
	;

lvalue
    : IDF ('[' exprList ']')? -> IDF ^(INDEX exprList)
    ;

exprList
    : expr exprList_1
    ;

exprList_1
    : ',' exprList -> exprList
    | ->
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
    | ->
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

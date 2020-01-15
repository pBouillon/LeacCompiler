grammar Grammar;

/*
 * --------------------
 * ANTLR options
 * --------------------
 */

options {
    // Grammar LL(1)
    
    k = 1;

    // Configure generation AST as output
    output = AST;
}

/*
 * --------------------
 * Headers
 * --------------------
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
 * Parser rules
 * --------------------
 */

@members {
    /**
      * Map variable name to Integer object holding value 
      */
    HashMap<String,Integer>  memory = new HashMap<String,Integer>();
}

program
	: PROGRAM IDF vardeclist funcdeclist instr WS* NEWLINE*
	;

vardeclist
    : vardeclist_1 vardeclist_1
    | 
    ;
    
vardeclist_1
    : varsuitdecl 
    | 
    ;

varsuitdecl
    : VAR identlist ':' typename ';'
    ;

identlist
    : IDF identlist_1
    ;
identlist_1
    : ',' identlist
    | 
    ;

typename 
	: atomtype
	| arraytype
	;

atomtype 
	:	VOID
	|	BOOL
	|	INT
	;
	
arraytype
	:	ARRAY '[' rangelist ']' OF atomtype
	;

rangelist
	:	CSTE '..' CSTE rangelist_1
	;

rangelist_1
	: ',' rangelist
	|
	;
	
funcdeclist
	: funcdecl funcdeclist
	;
	
funcdecl
	: FUNCTION IDF '(' arglist ')' ':' atomtype vardeclist instr
	;
	
arglist
	: arg arglist_1
	;

arglist_1
	: ',' arglist
	|
	;
	
arg	
	: IDF ':' typename
	| REF IDF ':' typename
	;
	
instr
	: IF expr THEN instr
	| WHILE expr DO instr
	| lvalue '=' expr
	| RETURN ret_1 
	| IDF '(' param
	| '{' end_sequence
	| READ lvalue
	| WRITE write_param
	;

ret_1
	: expr
	| 
	;
	
param 
	: ')'
	| exprList ')'
	;

write_param
	: lvalue
	| CSTE
	; 
	
end_sequence
	: sequence '}'
	| '}'
	;
	
sequence
	: instr sequence_1
	;

sequence_1
	: ';' sequence_2
	| '}' sequence_2
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
	: '[' exprList ']'
	| 
	;

exprList
	: expr exprList_1
	;
	
exprList_1
	: ',' exprList
	|
	;
	
expr 
	: expr_prime (plusminOps expr_prime)*
	;

expr_prime
	: expr_prime_bis (muldivOps expr_prime_bis)*
	;

expr_prime_bis
	: expr_final (compareOps expr_final)*
	;
	
expr_final
	: CSTE 
	| '(' expr ')'
	| opun expr
	| IDF expr_1
	;

expr_1
	: '(' expr_2
	| '[' exprList ']'
	|
	;

expr_2
	: exprList ')'
	| ')'
	;
		
/*
opb 
    : '+'
	| '-'
	| '*'
	| '/'
	| '^'
	| '<'
	| '<='
	| '>'
	| '>='
	| '=='
	| '!='
	| 'and'
	| 'or'
	;
	*/
    
compareOps
	: '<'
	| '<='
	| '>'
	| '>='
	| '=='
	| '!='
	;

muldivOps
	: '*'
	| '/'
	| '^'
	;

logicOps 
	: 'and'
	| 'or'
	;

plusminOps
	: '+'
	| '-'
	;

opun 
	: '-'
	| 'not'
    ;
		
/*
 * --------------------
 Lexer rules
 --------------------
 */

// Globals
IGNORE: (NEWLINE | WS)*;

NEWLINE: '\r'? '\n';

WS: (' ' | '\t')+ {$channel=HIDDEN;};

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
fragment CHARACTER: 'a' ..'z' | 'A' ..'Z';

IDF: CHARACTER (CHARACTER | DIGIT)*;

// Represent a constant of any kind
fragment CSTE_BOOL: 'true' | 'false';

// Represent a numeric constant
CSTE: CSTE_BOOL | CSTE_NUM | CSTE_STR;

fragment DIGIT: '0' ..'9';

fragment CSTE_NUM: DIGIT+;

// Represent a text constant
fragment CSTE_STR:
	CSTE_STR_DOUBLE_QUOTES
	| CSTE_STR_SINGLE_QUOTES;

fragment CSTE_STR_DOUBLE_QUOTES:
	'"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"';

fragment CSTE_STR_SINGLE_QUOTES:
	'\'' (~('\'' | '\\' | '\r' | '\n') | '\\' ('\'' | '\\'))* '\'';
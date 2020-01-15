grammar Grammar;

/*
 * --------------------
 ANTLR options
 --------------------
 */

options {
	// Grammar LL(1)
	k =;

	// Adding ignoring rule
	ignore = IGNORE;

	// Configure generation AST as output
	output = AST;
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

program: PROGRAM IDF vardeclist WS* NEWLINE*;

vardeclist: varsuitdecl vardeclist_1 |;

vardeclist_1: | varsuitdecl;

varsuitdecl: VAR identlist ':' typename ';';

identlist: IDF identlist_1;

identlist_1: ',' identlist |;

typename: 'int';
/*
 * --------------------
 Lexer rules
 --------------------
 */

// Globals
NEWLINE: '\r'? '\n';
WS: (' ' | '\t')+ {$channel=HIDDEN;};
IGNORE: (NEWLINE | WS)*;

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

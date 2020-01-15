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

prog
	: greetings WS* NEWLINE*
	;
	
greetings
	: 'Hello World !'
	;	

/*
 * --------------------
 * Lexer rules
 * --------------------
 */

// Globals
NEWLINE: '\r'? '\n';
WS: (' ' | '\t')+ {$channel=HIDDEN;};

// Keywords
ARRAY    : 'array';
BOOL     : 'bool';
DO		 : 'do';
ELSE     : 'else';
FUNCTION : 'function';
IF       : 'if';
INT      : 'int';
OF       : 'of';
PROGRAM  : 'program';
READ     : 'read';
REF      : 'ref';
RETURN   : 'return';
THEN     : 'then';
VAR  	 : 'var';
VOID     : 'void';
WHILE    : 'while';
WRITE    : 'write';

// Lexical aspects
// Represent an identifier
fragment CHARACTER : 'a'..'z' | 'A'..'Z';
IDF : CHARACTER (CHARACTER | DIGIT)*;

// Represent a constant of any kind
CSTE : CSTE_BOOL | CSTE_NUM | CSTE_STR;

// Represent a boolean constant
CSTE_BOOL : 'true' | 'false';

// Represent a numeric constant
fragment DIGIT: '0'..'9';
CSTE_NUM  : DIGIT+;

// Represent a text constant
fragment ESCAPED_DOUBLE_QUOTES : '\\"';
fragment ESCAPED_SINGLE_QUOTES : '\\\'';

CSTE_STR  
    : '"' (. | ESCAPED_DOUBLE_QUOTES)*? '"' 
    | '\'' (. | ESCAPED_SINGLE_QUOTES)*? '\''
    ;

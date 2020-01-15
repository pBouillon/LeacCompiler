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

// Keywords
ARRAY    = 'array';
BOOL     = 'bool';
DO		 = 'do';
ELSE     = 'else';
FUNCTION = 'function';
IF       = 'if';
INT      = 'int';
OF       = 'of';
PROGRAM  = 'program';
READ     = 'read';
REF      = 'ref';
RETURN   = 'return';
THEN     = 'then';
VAR  	 = 'var';
VOID     = 'void';
WHILE    = 'while';
WRITE    = 'write';

// Globals
NEWLINE: '\r'? '\n';
WS: (' ' | '\t')+ {$channel=HIDDEN;};

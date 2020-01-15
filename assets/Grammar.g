grammar Grammar;

/*
 * --------------------
 * ANTLR options
 * --------------------
 */

options {
    // Grammar LL(1)
    k = 1;
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

NEWLINE: '\r'? '\n';
WS: (' ' | '\t')+ {$channel=HIDDEN;};

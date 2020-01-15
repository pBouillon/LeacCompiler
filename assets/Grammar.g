grammar Grammar;

options {
    // Grammar LL(1)
    k = 1;
}

@lexer::header {
    package antlr.assets;
}

@parser::header {
    package antlr.assets;
    import java.util.HashMap;
}

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


NEWLINE: '\r'? '\n';
WS: (' ' | '\t')+ {$channel=HIDDEN;};

grammar Grammar;

@header {
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

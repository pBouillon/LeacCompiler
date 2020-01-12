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

prog: WS* NEWLINE*;

NEWLINE: '\r'? '\n';
WS: (' ' | '\t')+ {$channel=HIDDEN;};

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
    : PROGRAM IDF vardeclist funcdeclist instr WS* NEWLINE*
    ;

vardeclist
    : vardeclist_1 vardeclist 
    |
    ;

vardeclist_1
    : varsuitdecl 
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
    : VOID
    | BOOL
    | INT
    ;

arraytype
    : ARRAY '[' rangelist ']' OF atomtype
    ;

rangelist
    : atom '..' atom rangelist_1
    ;

rangelist_1
    : ',' rangelist 
    |
    ;

funcdeclist
    : funcdecl funcdeclist 
    |
    ;

funcdecl
    : FUNCTION IDF '(' arglist ')' ':' atomtype vardeclist '{' end_sequence
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
    : IF expr THEN instr (options {greedy = true; }: ELSE instr)* 
    | WHILE expr DO instr 
    | IDF instr_after_idf
    | RETURN ret_1
    | '{' end_sequence
    | READ lvalue 
    | WRITE write_param
    ;
    
instr_after_idf
	: lvalue_1 '=' expr
	| '(' param
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
    |
    ;

sequence_2: 
    sequence 
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
	: expr_parent (powerOps expr_parent)*
	;

expr_parent
	: '(' expr_compare ')' 
	| expr_final
	;
	
expr_final
    : CSTE 
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
    ;

powerOps 
	:	'^'
    ;

andOrOps
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

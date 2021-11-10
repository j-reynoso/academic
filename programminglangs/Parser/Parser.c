/* Jasmin Reynoso
* Fall 2021
* EBNF Tokenizer and Parser
* Programming Languages
* Professor Whitten
*/


#include "Parser.h"
#include "Givens.h"


struct lexics currentLex[LEXEME_MAX];
int index;
_Bool result;
int errors;

/*
function: parser
parameters: pointer to array of lexics and number of tokens in array
does:  takes the tokens given in the array passed and determines if they are
legal in the language defined by the grammar given for the assignment
returns: boolean value based on if any syntax errors are found; 
true if fits in grammar, false if it violates grammar rules
*/
_Bool parser(struct lexics *someLexics, int numberOfLexics) {
    index = 0;
    errors = 0;
    *currentLex = someLexics[index];
    result = 1;
    header(someLexics);
    if(errors == 0 && index == numberOfLexics-1) {
    return result;
    } else {
        return 0;
    }
}

/*
function: getNextLex
parameters: pointer to array of lexic structs
does: increments the value of the index so next lexeme may
be processed
*/
void getNextLex(struct lexics *someLexics) {
    index++;
    *currentLex = someLexics[index];
}

/*
function: header
parameters: pointer to array of lexic structs
does: checks for rules of
header --> VARTYPE IDENTIFIER LEFT_PARENTHESIS [arg-decl] RIGHT_PARENTHESIS
*/
void header(struct lexics *someLexics) {
    if(currentLex->token == 9) {
        getNextLex(someLexics);
        if(currentLex->token == 10) {
            getNextLex(someLexics);
            if(currentLex->token == 0) {
                getNextLex(someLexics);
                while(currentLex->token == 9) {
                    arg_decl(someLexics);
                }
                if(currentLex->token == 1) {
                    getNextLex(someLexics);
                    body(someLexics);
                    result = 1;
                } else {
                    errors = errors + 1;
                }
            } else {
                errors = errors + 1;
            }
        } else {
            errors = errors + 1;
        }
    } else {
        errors = errors + 1;
    }
}
/*
function: body
parameters: pointer to array of lexic structs
does: checks for rules of
body --> LEFT_BRACKET [statement-list] RIGHT_BRACKET
*/
void body(struct lexics *someLexics) {
    if(currentLex->token == 2) {
        getNextLex(someLexics);
        while(currentLex->token == 4 || currentLex->token == 5  || currentLex->token == 10  || currentLex->token == 2 ) {
            stmt_list(someLexics);
            getNextLex(someLexics);
            result = 1;
        }
        if(currentLex->token == 3) {
            result = 1;
            return;
        } else {
            errors = errors + 1;
        }
    } else {
        errors = errors + 1;
    }
}
/*
function: arg_decl
parameters: pointer to array of lexic structs
does: checks for rules of
arg_decl --> VARTYPE IDENTIFIER {COMMA VARTYPE IDENTIFIER}
*/
void arg_decl(struct lexics *someLexics) {
    if(currentLex->token == 9) {
        getNextLex(someLexics);
        if(currentLex->token == 10) {
            getNextLex(someLexics);
            while(currentLex->token == 7) {
                getNextLex(someLexics);
                if(currentLex->token == 9) {
                    getNextLex(someLexics);
                    if(currentLex->token == 10) {
                        getNextLex(someLexics);
                        result = 1;
                        return;
                    } 
                } else {
                    errors = errors + 1;
                }
            }
        result = 1;
        } else {
        errors = errors + 1;
     }
    } else {
         errors = errors + 1;
     }
}

/*
function: stmt_list
parameters: pointer to array of lexic structs
does: checks for rules of
stmt_list --> statement {statement}
*/
void stmt_list(struct lexics *someLexics) {
    while(currentLex->token == 4) {
        stmt(someLexics);
    } 
    while(currentLex->token == 5) {
        stmt(someLexics);
    } 
    while(currentLex->token == 10) {
        stmt(someLexics);
    }
    while(currentLex->token == 2) {
        stmt(someLexics);
    }
}

/*
function: stmt
parameters: pointer to array of lexic structs
does: checks for rules of
stmt        --> while-loop | return | assignment | body
while-loop 	--> WHILE_KEYWORD LEFT_PARENTHESIS expression RIGHT_PARENTHESIS statement
return 	    --> RETURN_KEYWORD expression EOL
assignment 	--> IDENTIFIER EQUAL expression EOL
*/
void stmt(struct lexics *someLexics) {
    if(currentLex->token == 4) {
        getNextLex(someLexics);
        if(currentLex->token == 0) {
            getNextLex(someLexics);
            expression(someLexics);
            if(currentLex->token == 1) {
                getNextLex(someLexics);
                stmt(someLexics);
            } else {
                errors = errors + 1;
            }
        } else {
            errors = errors + 1;
        }
    } else if(currentLex->token == 5) {
        getNextLex(someLexics);
        expression(someLexics);
        if(currentLex->token == 8) {
            return;
        } else {
            errors = errors + 1;
        }
    } else if(currentLex->token == 10) {
        getNextLex(someLexics);
        if(currentLex->token == 6) {
            getNextLex(someLexics);
            expression(someLexics);
            if(currentLex->token == 8) {
                return;
            } else {
                errors = errors + 1;
            }
        } else {
            errors = errors + 1;
        }
    } else if(currentLex->token == 2) {
        body(someLexics);
    } else {
        errors = errors + 1;
    }
}
/*
function: expression
parameters: pointer to array of lexic structs
does: checks for rules of
expression 	--> term {BINOP term} | LEFT_PARENTHESIS expression RIGHT_PARENTHESIS
term 		--> IDENTIFIER | NUMBER 
*/
void expression(struct lexics *someLexics) {
    if(currentLex->token == 10 || currentLex->token == 12) {
        getNextLex(someLexics);
        while(currentLex->token == 11) {
        getNextLex(someLexics);
        expression(someLexics);
        } 
        } else if(currentLex->token == 0) {
        getNextLex(someLexics);
        expression(someLexics);
        if(currentLex->token == 1) {
            getNextLex(someLexics);
            return;
        } else {
            errors = errors + 1;
        }
    } else {
        errors = errors + 1;
    }
}

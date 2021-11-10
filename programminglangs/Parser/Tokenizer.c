/* Jasmin Reynoso
* Fall 2021
* EBNF Tokenizer and Parser
* Programming Languages
* Professor Whitten
*/


#include "Tokenizer.h"
#include "Givens.h"

#include <stdio.h>
#include <ctype.h>
#include <string.h>

/*
function: tokenizer
parameters: takes array of lexics, pointer to number of tokens, and pointer to file
does: reads in character by character from file and uses logical conditions to determine
each individual word and character creating lexic structs, then tokenizes them. then adds
each created lexic struct to the array of lexemes passed to function
returns: boolean value based on if the tokenization process completes; 
true if successful, terminates if fails
*/
_Bool tokenizer(struct lexics *aLex, int *numLex, FILE *inf) {
    char ch = pullNextChar(inf);
    while(ch != EOF) {
        if(isalnum(ch) == 0 && isspace(ch) == 0) {
            char charDelim[MY_CHAR_MAX];
            charDelim[0] = ch;
            int cdIndex = 1;
            if(ch == '!' || ch == '=') {
                ch = pullNextChar(inf);
                if(ch == '=') {
                    charDelim[cdIndex] = ch;
                    cdIndex++;
                    ch = pullNextChar(inf);
                }
            } else {
                ch = pullNextChar(inf);
            }
            charDelim[cdIndex] = '\0';
            struct lexics newCharLex[LEXEME_MAX];
            strcpy(newCharLex->lexeme, charDelim);
            association(newCharLex, charDelim);
            *numLex = *numLex + 1;
            addToLexicsArray(aLex, *numLex - 1, *newCharLex);
        } else if(isalnum(ch) > 0) {
            char wordLexeme[MY_CHAR_MAX];
            wordLexeme[0] = ch;
            int index = 1;
            while(isalnum(ch) != 0) {
                ch = pullNextChar(inf);
                wordLexeme[index] = ch;
                index = index + 1;
            }
            wordLexeme[index - 1] = '\0';
            struct lexics newLex[LEXEME_MAX];
            strcpy(newLex->lexeme, wordLexeme);
            association(newLex, wordLexeme);
            *numLex = *numLex + 1;
            addToLexicsArray(aLex, *numLex - 1, *newLex);
        } else if(isspace(ch) > 0) {
            ch = pullNextChar(inf);
        }
    }
    return 1;
}

/*
function: addToLexicsArray
parameters: takes array of lexics, integer to keep track of index to add to, and lexeme struct
does: populates lexeme struct array with passed individual lexeme struct according to index value
*/
void addToLexicsArray(struct lexics *aLex, int index, struct lexics newLex) {
    strcpy(aLex[index].lexeme, newLex.lexeme);
    aLex[index].token = newLex.token;

}

/*
function: association
parameters: pointer to lexic struct newLex, and char pointer val
does: associates appropriate token to the token data member of each lexic
struct in the array of lexic structs passed
*/
void association(struct lexics *newLex, char *val) {
            if(strcmp(val,  "(") == 0) {
                newLex->token = 0;
            } else if(strcmp(val, ")") == 0) {
                newLex->token = 1;
            } else if(strcmp(val, "{") == 0) {
                newLex->token = 2;
            } else if(strcmp(val, "}") == 0) {
                newLex->token = 3;
            } else if(strcmp(val, "while") == 0) {
                newLex->token = 4;
            } else if(strcmp(val, "return") == 0) {
                newLex->token = 5;
            } else if(strcmp(val, "=") == 0) {
                newLex->token = 6;
            } else if(strcmp(val, ",") == 0) {
                newLex->token = 7;
            } else if(strcmp(val, ";") == 0) {
                newLex->token = 8;
            } else if(strcmp(val, "int") == 0 || strcmp(val, "void") == 0) {
                newLex->token = 9;
            } else if(validIdentifier(val)) {
                newLex->token = 10;
            }  else if(strcmp(val, "+") == 0 || strcmp(val, "*") == 0|| strcmp(val, "!=") == 0 || strcmp(val, "==") == 0 || strcmp(val, "%") == 0) {
                newLex->token = 11;
            } else if(validNumber(val)) {
                newLex->token = 12;
            }
}

/*
function: pullNextChar
parameters: pointer to file
does: pulls next char value in file
returns: char pulled
*/
char pullNextChar(FILE *inf) {
    char ch;
    ch = getc(inf);
    return ch;
}
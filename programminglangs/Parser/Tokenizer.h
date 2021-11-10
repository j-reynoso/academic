/* Jasmin Reynoso
* Fall 2021
* EBNF Tokenizer and Parser
* Programming Languages
* Professor Whitten
*/

#ifndef TOKENIZER_H
#define TOKENIZER_H

#include "Givens.h"
#include <stdio.h>


extern struct lexics currentLex[LEXEME_MAX];
extern int index;
extern _Bool result;
extern int errors;

_Bool tokenizer(struct lexics *aLex, int *numLex, FILE *inf);


void association(struct lexics *newLex, char *val);

void addToLexicsArray(struct lexics *aLex, int index, struct lexics newLex);

char pullNextChar(FILE *inf);


#endif
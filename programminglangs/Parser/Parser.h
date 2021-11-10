/* Jasmin Reynoso
* Fall 2021
* EBNF Tokenizer and Parser
* Programming Languages
* Professor Whitten
*/

#ifndef PARSER_H
#define PARSER_H

#include "Givens.h"

_Bool parser(struct lexics *someLexics, int numberOfLexics);
void getNextLex(struct lexics *someLexics);
void header(struct lexics *someLexics);
void body(struct lexics *someLexics);
void arg_decl(struct lexics *someLexics);
void stmt_list(struct lexics *someLexics);
void stmt(struct lexics *someLexics);
void expression(struct lexics *someLexics);

#endif
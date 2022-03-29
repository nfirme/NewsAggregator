lexer grammar AggregatorConfigLexer;

FILE_SOURCE_TYPE    : 'file' ;
URL_SOURCE_TYPE     : 'url' ;
FORMAT              : 'format:' ;
NEWSAPI_FORMAT      : 'newsapi' ;
SIMPLE_FORMAT       : 'simple' ;
NAME                : 'name:' -> pushMode(lineMode) ;
LOCATION            : 'location:' -> pushMode(lineMode) ;
ADDRESS             : 'address:' -> pushMode(lineMode) ;
FILTER              : 'filter:' ;
AND                 : '&' ;
OR                  : '|' ;
LPAREN              : '(' ;
RPAREN              : ')' ;
KEYWORD             : ~[\r\n()&| ]+ ;
NEWLINE             : '\r'? '\n' | '\r' ;
WS                  :  [ \t]+ -> skip ;
COMMENT             :  '#' .*? '\n' -> skip ;


mode lineMode;

LINE           : ~[\r\n]+ -> popMode ;

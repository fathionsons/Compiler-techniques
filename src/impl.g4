grammar impl;

start
    :   reset  EOF
    ;

reset
    :    oscillator*
    ;


oscillator
    :  input
    ;

oscillatorM
    : simulate #Output
    ;

and
    : #AND
    ;

input
    :   eq='='?'1'  and OscillatorM=oscillatorM #ONE
    |   eq='='?'0'  and OscillatorM=oscillatorM #ZERO
    ;

update
    : #Circuit
    ;

simulate
    :
    ;


RESET : [0-1];
WHITESPACE : [ \n\t\r]+ -> skip;
COMMENT : '//' ~('\n')* -> skip;
LONGCOMMENT : '/*' (~[*]+ | '*'~[/])* '*/' -> skip;
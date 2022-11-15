#!/bin/bash

expression(){
    local x=one
    expression_two
    echo "O valor de x agora é ${x}"    
}

expression_two(){
    x=two
}

expression
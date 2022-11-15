#!/bin/bash
x=10

expression(){
    local x=20
    expression_two
    echo "O valor de x no escopo de expression é ${x}"    
}

expression_two(){
    x=30
}

expression
echo "O valor de x no escopo global é ${x}"
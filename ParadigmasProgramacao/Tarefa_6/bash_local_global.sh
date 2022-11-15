#!/bin/bash

expression(){
    local y=50
    declare -g z=40  
}

expression
echo "O valor de x no escopo global é: ${y}"
echo "O Valor de z pode ser acessado do escopo global: ${z}"
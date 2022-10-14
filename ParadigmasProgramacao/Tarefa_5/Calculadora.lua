--  Calculadora ---

function Calculator(n1, n2, oper)
    if oper == "1" then io.write("\nO resultado de ", n1, " + ", n2, " é: ", n1 + n2, "\n")
    elseif oper == "2" then io.write("\nO resultado de ", n1, " - ", n2, " é: ", n1 - n2, "\n")
    elseif oper == "3"  then io.write("\nO resultado de ", n1, " * ", n2, " é: ", n1 * n2, "\n")
    elseif oper == "4" then io.write("\nO resultado de ", n1, " / ", n2, " é: ", n1 / n2, "\n")
    else print("Opção de menu inválida, tente novamente!")
    end
end

function PrintMenu()
    print("=-=-=- Calculadora -=-=-=")
    print("1 - Somar")
    print("2 - Subtrair")
    print("3 - Multiplicar")
    print("4 - Dividir")
end

function GetEntryData()
    local calculating = true;
    while calculating do
        print()
        print("Digite o primeiro número:")
        local a = io.read("*l")
        print("Digite o segundo número:")
        local b = io.read("*l")
        PrintMenu()
        local c = io.read("*l")
        Calculator(a,b,c)
        print("\nDeseja realizar nova operação (s/n): ")
        local nova_oper = io.read("*l")
        if nova_oper ~= "s" and nova_oper ~= "S" then
            calculating = false
        end
    end
end

GetEntryData();
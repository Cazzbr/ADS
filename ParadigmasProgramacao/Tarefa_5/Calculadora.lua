--  Calculadora ---


function Calculator(a,b)
    return a+b;
end


function GetEntryData()
    local a, b = io.read("*n","*n");
    local c = Calculator(a,b);
    print(c);
end

GetEntryData();
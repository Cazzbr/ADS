USE PORTAL_DA_TRANSPARENCIA;
# Questão 1:
DELETE FROM emendas
      WHERE nome_do_autor LIKE  "BANCADA%";

DELETE FROM emendas
      WHERE nome_do_autor LIKE  "RELATOR%";

DELETE FROM emendas
      WHERE nome_do_autor LIKE  "COM. MISTA, PLAN.ORC.E FISCALIZACAO";
      
# Questão 2:
SELECT nome_do_autor
    , SUM(valor_pago) val
  FROM emendas
GROUP BY nome_do_autor
HAVING SUM(valor_pago) = (SELECT MAX(x.val) 
                            FROM (SELECT nome_do_autor
                                       , SUM(valor_pago) val
                                    FROM emendas
                                GROUP BY nome_do_autor
                                ORDER BY val DESC) x);

# Alternativa para resposta;
SELECT nome_do_autor
     , SUM(valor_pago) gastos
  FROM emendas
GROUP BY nome_do_autor
ORDER BY gastos DESC LIMIT 1;

# Questão 3:
SELECT nome_do_autor
     , SUM(valor_pago) gastos
     , COUNT(*) Movimentacoes
  FROM emendas
GROUP BY nome_do_autor
ORDER BY gastos DESC LIMIT 10;

# Questão 4:;
SELECT funcao
    , SUM(valor_pago) gastos
  FROM emendas
  GROUP BY funcao
  ORDER BY gastos;

# Questão 5:
SELECT localidade_do_gasto
    , SUM(valor_pago) gastos
  FROM emendas
  GROUP BY localidade_do_gasto
  ORDER BY gastos;
  
# Questão 6:
SELECT localidade_do_gasto
    , SUM(valor_pago) gastos
    , COUNT(*) Movimentos
  FROM emendas
  GROUP BY localidade_do_gasto
  ORDER BY gastos DESC;
  
# Questão 7:
SELECT localidade_do_gasto localidade
    , valor_pago
    , (SELECT SUM(valor_pago) FROM emendas WHERE localidade_do_gasto = localidade) valor_total
    , funcao
    , subfuncao
  FROM emendas
  ORDER BY localidade_do_gasto, valor_pago DESC;
  
# Questão 8:
Select localidade_do_gasto
    , valor_pago
  FROM emendas
WHERE valor_pago = (SELECT MAX(valor_pago) FROM emendas);

# Questão 9:
SELECT localidade_do_gasto
    , SUM(valor_pago) gastos
  FROM emendas
  GROUP BY localidade_do_gasto
  HAVING gastos = (SELECT MAX(x.val) 
                     FROM (SELECT localidade_do_gasto
                                , SUM(valor_pago) val
                             FROM emendas
                         GROUP BY localidade_do_gasto) x);

# Questão 10:
SELECT *
  FROM emendas
  WHERE localidade_do_gasto like  "%RIO GRANDE DO SUL (UF)%";

# Questão 11:
SELECT nome_do_autor
    , SUM(RS.valor_pago) gasto
  FROM (SELECT nome_do_autor
             , valor_pago
             , localidade_do_gasto
          FROM emendas
         WHERE localidade_do_gasto LIKE "%RIO GRANDE DO SUL (UF)%") RS
      GROUP BY nome_do_autor
        HAVING gasto = (SELECT MAX(x.gasto)
                           FROM (SELECT nome_do_autor
                                      , SUM(RS.valor_pago) gasto
                                   FROM (SELECT nome_do_autor
                                              , valor_pago
                                              , localidade_do_gasto
                                           FROM emendas
                                          WHERE localidade_do_gasto LIKE "%RIO GRANDE DO SUL (UF)%") RS
                                       GROUP BY nome_do_autor
                                       ORDER BY gasto) x) 
                  OR
                  gasto = (SELECT MIN(x.gasto)
                             FROM (SELECT nome_do_autor
                                        , SUM(RS.valor_pago) gasto
                                     FROM (SELECT nome_do_autor
                                                , valor_pago
                                                , localidade_do_gasto
                                             FROM emendas
                                            WHERE localidade_do_gasto LIKE "%RIO GRANDE DO SUL (UF)%") RS
                                         GROUP BY nome_do_autor
                                         ORDER BY gasto) x);
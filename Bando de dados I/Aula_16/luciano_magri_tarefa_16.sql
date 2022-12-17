USE sistema_erp;

# 1. Mostre número da nota, nome do produto do item da nota, valor unitário do item da nota de
# todas as notas e itens, ordenados por número de nota.
SELECT nota_fiscal.numero
     , produto.nome
     , item_nota_fiscal.valor
  FROM nota_fiscal
  JOIN item_nota_fiscal ON (item_nota_fiscal.nota_fiscal_id = nota_fiscal.nota_fiscal_id)
  JOIN produto ON (produto.produto_id = item_nota_fiscal.produto_id)
ORDER BY nota_fiscal.numero;

# 2. Mostre a quantidade de notas tipo ENT por fornecedor. Se um fornecedor não tiver notas
# tipo ENT, ele deve aparecer mesmo assim.
# Ordene o resultado por contagem de notas ENT.
SELECT fornecedor.fornecedor_id
     , fornecedor.nome
     , coun.notas_ENT
  FROM fornecedor
LEFT JOIN (SELECT fornecedor_id
                , COUNT(*) notas_ENT
                , tipo
             FROM nota_fiscal
         GROUP BY fornecedor_id, tipo
           HAVING tipo = "ENT") coun ON (fornecedor.fornecedor_id = coun.fornecedor_id)
ORDER BY coun.notas_ENT;

# 3. Mostre a contagem total de notas, contagem total de itens de nota e contagem total de
# clientes, em uma linha.
SELECT *
  FROM (SELECT COUNT(*) Notas FROM nota_fiscal) nf
  JOIN (SELECT COUNT(*) Itens_nota FROM item_nota_fiscal) inf
  JOIN (SELECT COUNT(*) clientes FROM cliente) c;

# 4. Mostre a média de valor unitário dos itens faturados, das notas tipo SAI.
SELECT AVG(item_nota_fiscal.valor) "Média valor dos itens da nota"
  FROM item_nota_fiscal
  JOIN nota_fiscal ON (item_nota_fiscal.nota_fiscal_id = nota_fiscal.nota_fiscal_id)
WHERE nota_fiscal.tipo = "SAI";

# 5. Calcule o saldo de estoque dos itens. Lançamentos tipo ENT somam no saldo. Lançamentos
# tipo SAI, descontam.
SELECT outter.nome_ent
     , outter.nome_out
     , ((CASE WHEN isnull(outter.qtde_ent) THEN 0 ELSE outter.qtde_ent END)
     - (CASE WHEN isnull(outter.qtde_out) THEN 0 ELSE outter.qtde_out END)) estoque
  FROM (SELECT * 
          FROM (SELECT produto.nome nome_ent
                     , nota_fiscal.tipo tipo_ent
                     , sum(item_nota_fiscal.quantidade) qtde_ent
                  FROM nota_fiscal
                  JOIN item_nota_fiscal ON (item_nota_fiscal.nota_fiscal_id = nota_fiscal.nota_fiscal_id)
                  JOIN produto ON (produto.produto_id = item_nota_fiscal.produto_id)
              GROUP BY produto.nome, nota_fiscal.tipo
                HAVING nota_fiscal.tipo = "ENT") entradas
    LEFT JOIN(SELECT produto.nome nome_out
                   , nota_fiscal.tipo tipo_out
                   , sum(item_nota_fiscal.quantidade) qtde_out
                FROM nota_fiscal
                JOIN item_nota_fiscal ON (item_nota_fiscal.nota_fiscal_id = nota_fiscal.nota_fiscal_id)
                JOIN produto ON (produto.produto_id = item_nota_fiscal.produto_id)
            GROUP BY produto.nome, nota_fiscal.tipo
              HAVING nota_fiscal.tipo = "SAI") saidas ON (entradas.nome_ent = saidas.nome_out)
        UNION
       SELECT * FROM (SELECT produto.nome nome_ent
                           , nota_fiscal.tipo tipo_ent
                           , sum(item_nota_fiscal.quantidade) qtde_ent
                        FROM nota_fiscal
                        JOIN item_nota_fiscal ON (item_nota_fiscal.nota_fiscal_id = nota_fiscal.nota_fiscal_id)
                        JOIN produto ON (produto.produto_id = item_nota_fiscal.produto_id)
                    GROUP BY produto.nome, nota_fiscal.tipo
                      HAVING nota_fiscal.tipo = "ENT") entradas
      RIGHT JOIN(SELECT produto.nome nome_out
                      , nota_fiscal.tipo tipo_out
                      , sum(item_nota_fiscal.quantidade) qtde_out
                   FROM nota_fiscal
                   JOIN item_nota_fiscal ON (item_nota_fiscal.nota_fiscal_id = nota_fiscal.nota_fiscal_id)
                   JOIN produto ON (produto.produto_id = item_nota_fiscal.produto_id)
               GROUP BY produto.nome, nota_fiscal.tipo
                 HAVING nota_fiscal.tipo = "SAI") saidas ON (entradas.nome_ent = saidas.nome_out)) outter;


# 6. Faça um script para atualizar o valor total dos itens da nota usando (quantidade * valor).
UPDATE item_nota_fiscal 
   SET valor_total = (quantidade * valor);

# 7. Faça um script para atualizar o valor total das notas com base no valor total de seus itens.;
UPDATE nota_fiscal
   SET valor_total_nota = (SELECT sum(item_nota_fiscal.valor_total)
                             FROM item_nota_fiscal
                         GROUP by item_nota_fiscal.nota_fiscal_id
                           HAVING item_nota_fiscal.nota_fiscal_id = nota_fiscal.nota_fiscal_id);
                           
# 8. Mostre as notas sem itens.
SELECT nota_fiscal.*
  FROM nota_fiscal
  LEFT JOIN item_nota_fiscal ON (item_nota_fiscal.nota_fiscal_id = nota_fiscal.nota_fiscal_id)
  WHERE isnull(item_nota_fiscal_id);

# 9. Mostre o valor total máximo e mínimo e médio de nota tipo ENT e o valor máximo e
# mínimo e médio de tipo SAI, e o máximo e mínimo e médio de todos os tipos.
# Tudo em um resultado de uma só linha.
SELECT * 
  FROM (SELECT MAX(nota_fiscal.valor_total_nota) MAX_ENT
             , MIN(nota_fiscal.valor_total_nota)  MIN_ENT
             , AVG(nota_fiscal.valor_total_nota)  AVG_ENT
          FROM nota_fiscal
         WHERE nota_fiscal.tipo = "ENT") ent
JOIN (SELECT MAX(nota_fiscal.valor_total_nota) MAX_SAI
           , MIN(nota_fiscal.valor_total_nota)  MIN_SAI
           , AVG(nota_fiscal.valor_total_nota)  AVG_SAI
        FROM nota_fiscal
       WHERE nota_fiscal.tipo = "SAI") sai
JOIN (SELECT MAX(nota_fiscal.valor_total_nota) MAX_TOTAL
           , MIN(nota_fiscal.valor_total_nota)  MIN_TOTAL
           , AVG(nota_fiscal.valor_total_nota)  AVG_TOTAL
        FROM nota_fiscal) total;
        
# 10.  Os seguintes SELECTS retornam resultados diferentes. Por quê?
SELECT produto.nome
, preco_de_custo.preco
FROM preco_de_custo
LEFT JOIN produto ON(produto.produto_id = preco_de_custo.produto_id
AND produto.nome LIKE '%Kiwi%'
);
SELECT produto.nome
, preco_de_custo.preco
FROM preco_de_custo
LEFT JOIN produto ON(produto.produto_id = preco_de_custo.produto_id)
WHERE produto.nome LIKE '%Kiwi%';
 
# O motivo é que com o LEFT JOIN, a tabla do FROM é retornada inteira, portanto, todas as linhas onde 
# não existe correlaçao do nome são retornadas. Já no segundo select, o filtro por nome é aplicado 
# no WHERE, desta forma o filtro produto.nome LIKE '%Kiwi%' é aplicado depois do retorno do join
# fazendo com que apareçam apenas as linhas em que relamente contenha 'Kiwi', apenar do LEFT JOIN.

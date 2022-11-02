USE sakila;

# 1. Quais atores têm no primeiro nome a sequência de caracteres lett?
SELECT *
  from actor
  where first_name LIKE "%lett%";
  
# 2. Quais filmes possuem trailers?
SELECT *
  FROM film
 WHERE special_features LIKE "%trailer%";
 
 # 3. Quais os tipos de classificações indicativas (rating) existentes nos cadastros de filmes?
 SELECT DISTINCT rating
   FROM film;
   
# 4. Quais filmes possuem drama na descrição e classificação indicativa (rating) contendo a
#    sequência de caracteres PG?
SELECT * 
  FROM film
  WHERE description LIKE "%drama%"
	AND rating LIKE "%pg%";
    
# 5.Faça uma consulta com uma projeção mostrando o tamanho da descrição de cada filme,
# seguido de todos os outros atributos da tabela de filmes, ordenando de maneira decrescente,
# da maior descrição para a menor, somente dos filmes cujo custo de reposição
# (replacement_cost) esteja entre 20 e 25.
SELECT length(description) "Tamanho da Descrição" 
	  , film.*
  FROM film
  WHERE replacement_cost BETWEEN 20 AND 25
ORDER BY length(description) DESC;

# 6.Faço uma consulta das cidades projetando todas as colunas mais uma, na qual caso o código
# do país do registro seja 15, mostre Brasil, caso seja 104, mostre Espanha, 49 mostre Itália, e
# para os outros casos mostre ‘NA’.
SELECT *
     , CASE country_id
		WHEN 15 THEN "Brasil"
		WHEN 104 THEN "Espanha"
		WHEN 49 THEN "Itália"
		ELSE "NA"
       END "País"
  FROM city;

# 7. Encontre todos os atores (actor) cujo primeiro nome é palíndromo.
SELECT *
  FROM actor
  WHERE first_name = "palíndromo";

# 8.Encontre todos os clientes (customer) cujo primeiro nome tem o mesmo número de letras
# que o segundo, ou que o e-mail tem um a antes da letra anterior ao @.
SELECT *
  FROM customer
WHERE length(first_name) = length(last_name)
   OR email like "%a_@%";
USE sakila;

# Selecionar as colunas de paises para verificar as possibildiades de JOIN
SELECT *
  FROM country;
  
# Selecionar as colunas de cidades para verificar as possibildiades de JOIN
SELECT *
  FROM city;
  
# Motrar as cidadades presentes na tabela city, com o pais correspondente na tabela country
# Para isso, utilizou-se um (inner) join entre as tabelas city e coutry retornano as colunas em questão.  
SELECT  city.city_id
      , city.city
      , country.country
  FROM city
  JOIN country ON (city.country_id = country.country_id)
  ORDER BY city.city_id;

# Selecionar as endereços de paises para verificar as possibildiades de JOIN
SELECT *
  FROM address;
  
# Mostrar todos os endereços e se algum funcionario reside no local
# Usou-se um LEFT Join entre address e staff, desta forma com LEFT join todos as endereços sao mostrados;
SELECT address.address_id
    , address.address
    , address.district
    , address.postal_code
    , staff.staff_id
    , staff.first_name
    , staff.last_name
  FROM address
LEFT JOIN staff ON (address.address_id = staff.staff_id);
SELECT *
  FROM film;
  
  
# Encontrar todas as linguas cadastradas e mostrar quais filmes temos registros em cadaa lingua
# RIGHT (OUTER) JOIN - com filmes join linguas
SELECT film.film_id
     , film.title
     , film.description
     , film.release_year
     , film.length
     , film.rating
     , film.special_features
     , language.name
  FROM film
RIGHT JOIN language on (film.language_id = language.language_id)
ORDER BY film.title;

# Selecionar as colunas de atores para verificar as possibildiades de JOIN
SELECT *
  FROM actor;

# UNION ALL com LEFT JOIN e RIGHT JOIN - teriamos o mesmo resultado apenas com um JOIN neste caso;
# Descobrir todos os atores de cada filme;
SELECT film.film_id
     , film.title
     , actor.first_name
     , actor.last_name
  FROM film
  LEFT JOIN film_actor on (film_actor.film_id = film.film_id)
  LEFT JOIN actor on (film_actor.actor_id = actor.actor_id)
  UNION ALL
  SELECT film.film_id
     , film.title
     , actor.first_name
     , actor.last_name
  FROM film
  RIGHT JOIN film_actor on (film_actor.film_id = film.film_id)
  RIGHT JOIN actor on (film_actor.actor_id = actor.actor_id);
  
# UNION ALL para verificar as linguas em que um filme certamente está disponível .
# O resultado é o mesmo pois nesse dump nenhum cadastro tem lingua original
SELECT film.film_id
     , film.title
     , film.description
     , film.release_year
     , language.name
  FROM film
  JOIN language ON (film.language_id = language.language_id)
  UNION ALL
  SELECT film.film_id
     , film.title
     , film.description
     , film.release_year
     , language.name
    FROM film
    JOIN language ON (film.original_language_id = language.language_id);
    
# Selecionar todos os filmes que lingua não é ingles
# Neste Dump todos os filmes sao em ingles.
SELECT *
  FROM film
  WHERE film.language_id NOT IN (SELECT language.language_id
                                   FROM language
                                  WHERE language.name = "ENGLISH");
                                  
# CROSS JOIN quantos personagens teriamos se todos os autores participarem de todos os filmes
# CROSS JOIN uma coluna de cada tabela;
SELECT actor.first_name
     , film.title
  FROM actor
CROSS JOIN film;

# SELF JOIN - Verificar se algum endereço tem address e address2 como mesmo valor
SELECT addr1.*
  FROM address addr1
  JOIN address addr2 ON (addr1.address_id = addr2.address_id
                     AND addr1.address = addr2.address2);
# Join enter film_text e film, percebe-se que a table "film_Text" tem colunas duplucadas com as colunas da tabela film.
# Isso é um problema para integridade do banco pois podemos atualizar uma tabela e não atualizar a outra.
SELECT film.film_id
     , film.title
     , film.description
     , film_text.title
     , film_text.description
  FROM film
  JOIN film_text ON (film.film_id = film_text.film_id);

# Select com dois joins para relacionar as categoruias de cada filme
SELECT film.film_id
     , film.title
     , film.description
     , film.special_features
     , category.name
  FROM film
  JOIN film_category on (film.film_id = film_category.film_id)
  JOIN category on (film_category.category_id = category.category_id);

# Verificar os endereço de cada funcionário
SELECT *
  FROM staff
  JOIN address on (staff.address_id = address.address_id);

# Vericiar os valores pagos e as datas de devolução dos filmes por cada cliente
SELECT customer.customer_id
     , customer.first_name
     , customer.last_name
     , customer.email
     , payment.amount
     , rental.return_date
  FROM customer
  JOIN payment ON (payment.customer_id = customer.customer_id)
  JOIN rental ON (rental.rental_id = payment.rental_id);

# Verificar o estoque de cada unidade
SELECT *
  FROM store
  JOIN inventory ON (inventory.store_id = store.store_id)
  JOIN film ON (film.film_id = inventory.film_id);

# Verificar os endereços dos clientes
SELECT customer.first_name
     , customer.last_name
     , address.address
  FROM customer
  JOIN address ON (address.address_id = customer.address_id);

# Lista dos atores para cada filme
SELECT film.title
     , actor.first_name
  FROM film
  JOIN film_actor ON (film_actor.film_id = film.film_id)
  JOIN actor ON (actor.actor_id = film_actor.actor_id);
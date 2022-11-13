USE imdb_full;

# 1 - Contar quantos filmes cada autor escreveu? 
SELECT writers.name
     , COUNT(writers.name) number_of_Films
  FROM movies
  JOIN movies2writers ON (movies.movieid = movies2writers.movieid)
  JOIN writers ON (writers.writerid = movies2writers.writerid)
GROUP BY writers.name;

# 2 - Verificar quantos filmes cada produtor produziu?
SELECT producers.name
     , COUNT(*) number_of_Films
  FROM movies
  JOIN movies2producers ON (movies.movieid = movies2producers.movieid)
  JOIN producers ON (producers.producerid = movies2producers.producerid)
  GROUP BY producers.name
  ORDER BY number_of_Films DESC;
  
# 3 - Verificar a relação entre a média de bilheteria dos filmes, classificados por avaliação.
# Verificasse que os filmes melhores classificados não tem, necessariamente, a melhor bilheteria, na média.
SELECT directors.rate
       , AVG(directors.gross) bilheteria
   FROM directors
GROUP BY directors.rate
ORDER BY directors.rate DESC;

# 4 - Verificar quantos votos todos os filmes de um mesmo ano receberam.
SELECT movies.year
     , SUM(ratings.votes) year_votes
  FROM movies
  JOIN ratings ON (movies.movieid = ratings.movieid)
GROUP BY movies.year
ORDER BY year_votes DESC;

# 5 - Quanto foi a maior bilheteria registrada para um diretor
SELECT MAX(directors.gross) Maior_Bilheteria
  FROM directors;
  
# 6 - Quantos atores são do sexo feminino e quantos do sexo masculino
SELECT CASE actors.sex
        WHEN "M" THEN "Masculino"
        WHEN "F" THEN "Feminino" END Sexo
     , COUNT(*)
  FROM actors
GROUP BY sex;

# 7 - Quantos filmes existem em cada lingua
SELECT language.language
     , COUNT(*) number_movies_per_language
  FROM movies
  JOIN language ON (movies.movieid = language.movieid)
GROUP BY language.language
ORDER BY number_movies_per_language DESC;

# 8 - Quantos filmes foram lançados em cada ano e estão presentes na DB
SELECT year
     , COUNT(*) 
  FROM movies
GROUP BY year
ORDER BY year DESC;

# 9 - Quantos filmes de drama estão na DB
SELECT genres.genre
     , COUNT(*) number_of_films
  FROM genres
  WHERE genres.genre = "Drama"
GROUP BY genres.genre;

# 10 - Quantos filmes de cada país?
SELECT countries.country
     , COUNT(*) AS number_films
  FROM countries
  JOIN movies ON (countries.movieid = movies.movieid)
GROUP BY countries.country
ORDER BY number_films DESC;

# 11 - Quantos filmes cada editor editou
SELECT editors.name
     , COUNT(*) AS movies_edited
  FROM movies
  JOIN movies2editors ON (movies.movieid = movies2editors. movieid)
  JOIN editors ON (editors.editorid = movies2editors.editorid)
GROUP BY editors.name;

# 12 - Qual a soma e média dos tempos dos filmes de cada ano
SELECT movies.year
     , SUM(runningtimes.time) runningtimes_time_sum
     , AVG(runningtimes.time) runningtimes_time_AVG
  FROM movies
  JOIN runningtimes ON (movies.movieid = runningtimes.movieid)
GROUP BY movies.year
ORDER BY runningtimes_time_sum DESC;

# 13 - Verificar quais filmes tem duas versões (uma norma e uma extendida, por exemplo)
SELECT movies.title
     , COUNT(*) as count_versions
  FROM runningtimes
  JOIN movies ON (movies.movieid = runningtimes.movieid)
GROUP BY movies.title
HAVING count_versions > 1;

# 14 - Quantos diretores tem rating acima de 50
SELECT COUNT(*) number_of_directors_with_rating_over_100
  FROM directors
WHERE rate > 50;

# 15 - Qual a média dos rank de todos os filmes de um diretor, a média do número de votos que o filme recebeu e número total de votos.
SELECT directors.name AS directors_name
     , AVG(ratings.rank) AS AVG_movies_rank
     , AVG(ratings.votes) AS AVG_movies_votes
     , sum(ratings.votes) AS SUM_movies_votes
  FROM directors
  JOIN movies2directors ON (directors.directorid = movies2directors.directorid)
  JOIN movies ON (movies2directors.movieid = movies.movieid)
  JOIN ratings ON (movies.movieid = ratings.movieid)
GROUP By directors.name;
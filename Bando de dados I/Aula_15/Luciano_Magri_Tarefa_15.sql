USE stats;

# Exercicio 1:
SELECT DISTINCT PostTypeID 
  FROM posts;

# Exercicio 2:
CREATE TABLE PostTypes  (Id INT PRIMARY KEY AUTO_INCREMENT
                        , Description VARCHAR(100)
                        );
INSERT INTO PostTypes VALUES(1, "Question")
                            ,(2, "Answer")
                            ,(3, "Orphaned tag Wiki")
                            ,(4, "Tag Wiki except")
                            ,(5, "Tag Wiki")
                            ,(6, "Moderator Nomination")
                            ,(7, "Wiki placeholder")
                            ,(8, "Privilege Wiki");

ALTER TABLE posts 
ADD CONSTRAINT PostTypes_posts_FK FOREIGN KEY (PostTypeId)
                                   REFERENCES PostTypes (Id);

# Exercicio 3:
SELECT PostTypes.Description
        , count(*) QTDE
  from posts
  JOIN PostTypes ON (posts.PostTypeId = PostTypes.Id)
GROUP BY posts.PostTypeId
HAVING PostTypes.Description = "Question";

# Exercicio 4:
SELECT posts.Title
     , posts.Body
     , comments.Text
  FROM posts
  JOIN comments ON (posts.Id = comments.PostId)
  WHERE posts.Title = "What is normality?";
  
# Exercicio 5;
SELECT p1.Title
     , p1.Body PostBody
     , p2.body AcceptedAnswer
  FROM posts p1
  JOIN posts p2 ON (p1.AcceptedAnswerId = p2.id);

# Exercicio 6:
SELECT CommentCount
     , AcceptedAnswerId
  FROM posts
ORDER BY AcceptedAnswerId, CommentCount DESC;

# Exercicio 7;
SELECT *
  FROM posts
WHERE CommentCount = (SELECT MAX(CommentCount)
                        FROM posts
                    );
# Exercicio 8:
SELECT posts.Title
     , posts.CommentCount
     , comments.Text
  FROM posts
  JOIN PostTypes ON (posts.PostTypeId = PostTypes.Id)
  JOIN comments ON (posts.Id = comments.PostId)
  WHERE isnull(posts.AcceptedAnswerId)
  AND PostTypes.Description = "Question";
  
# Exercicio 9
SELECT posts.Title
    , posts.score
  FROM posts
  JOIN PostTypes ON (posts.PostTypeId = PostTypes.Id)
 WHERE PostTypes.Description = "Question"
ORDER BY score;

# Exercicio 10:
SELECT DisplayName
  FROM users
GROUP BY DisplayName
ORDER BY COUNT(*) DESC;

# Exercicio 11:
SELECT users.DisplayName
     , COUNT(*) QTDE
     , SUM(Reputation) Reputation
  FROM users
GROUP BY DisplayName
ORDER BY QTDE DESC, Reputation DESC
LIMIT 10;

# Exercicio 12:
SELECT users.DisplayName
     , AVG(Reputation) AVG_REPUTATION
  FROM users
GROUP BY DisplayName
ORDER BY AVG_REPUTATION DESC;

# Exercicio 13;
SELECT p1.Title
     , p1.Body PostBody
     , p2.body AcceptedAnswer
  FROM posts p1
  JOIN PostTypes ON (p1.PostTypeId = PostTypes.Id)
  LEFT JOIN posts p2 ON (p1.AcceptedAnswerId = p2.id)
  WHERE PostTypes.Description = "Question"
  AND p1.Tags LIKE "%<database>%";

# Exercicio 14:
SELECT *
FROM (Select count(*) users 
        FROM users) usr
        JOIN (SELECT count(*) Perguntas 
                FROM posts 
                JOIN PostTypes ON (posts.PostTypeId = PostTypes.Id) 
               WHERE PostTypes.Description = "Question") pq
        JOIN (SELECT count(*) Respostas 
                FROM posts 
                JOIN PostTypes ON (posts.PostTypeId = PostTypes.Id) 
               WHERE PostTypes.Description = "Answer") resp
        JOIN (SELECT count(*) comments 
        FROM comments) cmt;
        
# Exercicio 15:
SELECT users.DisplayName
     , COUNT(*) QTDE
     , SUM(Reputation) Reputation
  FROM users
GROUP BY DisplayName
ORDER BY QTDE DESC, Reputation DESC;

# Exercicio 16:
SELECT posts.*
  FROM posts
 WHERE OwnerUserId = (SELECT users.Id
                        FROM users
                       WHERE CreationDate = (SELECT MIN(CreationDate)
                                               FROM users))
  AND PostTypeId = 1;
  
# Exercicios 17:
SELECT posts.Title
      , count(*) c
  FROM  posts
GROUP BY posts.Title
HAVING posts.PostTypeId = 1;

# Exercicios 18:
SELECT * 
  FROM badges
 WHERE UserId = (SELECT usrID
                   FROM(SELECT respostas.OwnerUserId usrID
                              , count(*) resp
                           FROM (SELECT *
                                   FROM posts 
                                  WHERE PostTypeId =2 and not isnull(OwnerUserId)) respostas
                               GROUP BY respostas.OwnerUserId
                                  HAVING resp = (SELECT MAX(maxResp.resp)
                                                   FROM(SELECT count(*) resp
                                                          FROM (SELECT *
                                                                  FROM posts 
                                                                 WHERE PostTypeId =2 and not isnull(OwnerUserId)) respostas
                                                              GROUP BY respostas.OwnerUserId) maxResp
                                                 )) usr_id);
                                                 
                                                 
# Exercicio 19;

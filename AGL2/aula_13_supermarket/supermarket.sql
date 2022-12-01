CREATE SCHEMA supermarket;

USE supermarket;

CREATE TABLE supermercado ( id                  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
                          , nome                VARCHAR(100)
                          , endereco            VARCHAR(100)
                          , cnpj                VARCHAR(100)
                          , gerente             INT UNSIGNED
                          );

CREATE  TABLE mercadoria  ( id                  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
                          , codigo              INT UNSIGNED UNIQUE NOT NULL
                          , descricao           VARCHAR(100) UNIQUE NOT NULL
                          , valor               DECIMAL(10,2) NOT NULL
                          , quantidade_estoque  INT DEFAULT 0
                          , supermercado_id     INT UNSIGNED
                          );

CREATE  TABLE funcionario ( id                  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
                          , nome                VARCHAR(100) NOT NULL
                          , funcao              VARCHAR(100)
                          , endereco            VARCHAR(100)
                          , cpf                 VARCHAR(100)
                          , login               VARCHAR(100) UNIQUE NOT NULL
                          , senha               VARCHAR(100)
                          , supermercado_id     INT UNSIGNED
                          );
                          
                          
CREATE  TABLE venda       ( id                  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
                          , data_venda          DATE
                          , valor_total         DECIMAL(10,2)
                          , funcionario_id      INT UNSIGNED
                          );

CREATE  TABLE venda_mercadoria ( id                 INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
                               , quantidade_produto INT UNSIGNED
                               , venda_id           INT UNSIGNED
                               , mercadoria_id      INT UNSIGNED
                               );

ALTER TABLE supermercado
ADD CONSTRAINT funcionario_supermercado_fk FOREIGN KEY (gerente)
                                        REFERENCES funcionario(id) ON DELETE SET NULL;

ALTER TABLE funcionario
ADD CONSTRAINT supermercado_funcionario_fk FOREIGN KEY (supermercado_id)
                                        REFERENCES supermercado(id);

ALTER TABLE mercadoria
ADD CONSTRAINT supermercado_mercadoria__fk FOREIGN KEY (supermercado_id)
                                        REFERENCES supermercado(id);

ALTER TABLE venda 
ADD CONSTRAINT funcionario_venda_fk FOREIGN KEY (funcionario_id)
                                        REFERENCES funcionario(id) ON DELETE SET NULL;

ALTER TABLE venda_mercadoria 
ADD CONSTRAINT venda_venda_mercadoria_fk FOREIGN KEY (venda_id) 
                                        REFERENCES venda(id) ON DELETE CASCADE;

ALTER TABLE venda_mercadoria 
ADD CONSTRAINT mercadoria_venda_mercadoria_fk FOREIGN KEY (mercadoria_id) 
                                        REFERENCES mercadoria(id) ON DELETE CASCADE;

INSERT INTO supermercado VALUES ( 1
                                , " "
                                , " "
                                , " "
                                , NULL
                                );

# DROP SCHEMA supermarket;
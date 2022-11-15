CREATE SCHEMA supermarket;

USE supermarket;

CREATE TABLE supermercado ( id                  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
                          , nome                VARCHAR(100)
                          , endereco            VARCHAR(100)
                          , gerente             INT UNSIGNED
                          );

CREATE  TABLE mercadoria  ( id                  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
                          , codigo              INT UNSIGNED UNIQUE NOT NULL
                          , descricao           VARCHAR(100) NOT NULL
                          , valor               DECIMAL(10,2) NOT NULL
                          , quantidade_estoque  INT UNSIGNED DEFAULT 0
                          , supermercado_id     INT UNSIGNED
                          );

CREATE  TABLE empregado   ( id                  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
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
                          , empregado_id        INT UNSIGNED
                          );

CREATE  TABLE venda_mercadoria ( id                  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
                               , valor_mercadoria    DECIMAL(10,2)
                               , venda_id            INT UNSIGNED
                               , mercadoria_id     INT UNSIGNED
                               );

ALTER TABLE mercadoria 
ADD CONSTRAINT supermercado_mercadoria_fk FOREIGN KEY (supermercado_id) 
                                        REFERENCES supermercado(id);

ALTER TABLE empregado 
ADD CONSTRAINT supermercado_empregado_fk FOREIGN KEY (supermercado_id) 
                                        REFERENCES empregado(id);
                                        
ALTER TABLE venda 
ADD CONSTRAINT empregado_venda_fk FOREIGN KEY (empregado_id) 
                                        REFERENCES empregado(id);

ALTER TABLE venda_mercadoria 
ADD CONSTRAINT venda_venda_mercadoria_fk FOREIGN KEY (venda_id) 
                                        REFERENCES venda(id);

ALTER TABLE venda_mercadoria 
ADD CONSTRAINT mercadoria_venda_mercadoria_fk FOREIGN KEY (mercadoria_id) 
                                        REFERENCES mercadoria(id);


#DROP SCHEMA supermarket;
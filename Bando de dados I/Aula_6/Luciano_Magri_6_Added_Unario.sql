CREATE SCHEMA sm_db_devel;

USE sm_db_devel;

CREATE TABLE super_mercado  ( super_mercado_id  INT
                            , descricao         VARCHAR(100)
                            , endereco          VARCHAR(100)
                            , responsavel_id    INT
                        );

CREATE TABLE funcionario( funcionario_id    INT
                        , descricao         VARCHAR(100)
                        , salario           DECIMAL
                        , data_admissao     DATE
                        , tipo_contrato     VARCHAR(100)
                        , super_mercado_id  INT
                        );

CREATE TABLE prestador_servicos ( prestador_servicos_id INT
                                , descricao             VARCHAR(100)
                                , tipo_servico          VARCHAR(100)
                                , data_contrato         DATE
                                , data_renovacao        DATE
                                , data_pagamento        DATE
                                , super_mercado_id      INT
                                );

CREATE TABLE imobilizados   ( imobilizados_id           INT
                            , descricao                 VARCHAR(100)
                            , valor                     DECIMAL
                            , data_aquisicao            DATE
                            , tempo_depreciacao_anos    INT
                            , super_mercado_id          INT
                        );

CREATE TABLE faturamento    ( faturamento_id    INT
                            , descricao         VARCHAR(100)
                            , data_faturamento  DATE
                            , valor_faturamento DECIMAL
                            , super_mercado_id  INT
                        );
CREATE TABLE coworker(funcionario_id1 INT UNSIGNED
                    , funcionario_id2 INT UNSIGNED
                    );

ALTER TABLE super_mercado MODIFY id INT
PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE super_mercado MODIFY descricao VARCHAR(100) NOT NULL;

ALTER TABLE funcionario MODIFY id INT
PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE funcionario MODIFY descricao VARCHAR(100) NOT NULL;

ALTER TABLE prestador_servicos MODIFY id INT
PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE prestador_servicos MODIFY descricao VARCHAR(100) NOT NULL;

ALTER TABLE imobilizados MODIFY id INT
PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE imobilizados MODIFY descricao VARCHAR(100) NOT NULL;

ALTER TABLE faturamento MODIFY id INT
PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE faturamento MODIFY descricao VARCHAR(100) NOT NULL;

ALTER TABLE super_mercado 
ADD CONSTRAINT funcionario_super_mercado_fk FOREIGN KEY (funcionario_id) 
                                        REFERENCES funcionario(funcionario_id);

ALTER TABLE funcionario 
ADD CONSTRAINT super_mercado_funcionario_fk FOREIGN KEY (super_mercado_id) 
                                        REFERENCES super_mercado(super_mercado_id);

ALTER TABLE prestador_servicos 
ADD CONSTRAINT super_mercado_prestador_servicos_fk FOREIGN KEY (super_mercado_id) 
                                        REFERENCES super_mercado(super_mercado_id);

ALTER TABLE imobilizados 
ADD CONSTRAINT super_mercado_imobilizados_fk FOREIGN KEY (super_mercado_id) 
                                        REFERENCES super_mercado(super_mercado_id);

ALTER TABLE faturamento 
ADD CONSTRAINT super_mercado_faturamento_fk FOREIGN KEY (super_mercado_id) 
                                        REFERENCES super_mercado(super_mercado_id);
                                        
ALTER TABLE coworker ADD CONSTRAINT funcionario_coworker_fk1 FOREIGN KEY(funcionario_id1)
                                                   REFERENCES funcionario(funcionario_id);
                                                   
ALTER TABLE coworker ADD CONSTRAINT funcionario_coworker_fk2 FOREIGN KEY(funcionario_id2)
                                                   REFERENCES funcionario(funcionario_id);

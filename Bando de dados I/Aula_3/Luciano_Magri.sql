CREATE SCHEMA sm_db_devel;

USE sm_db_devel;

CREATE TABLE super_mercado  ( id                INT
                            , descricao         VARCHAR(100)
                            , endereco          VARCHAR(100)
                            , responsavel_id    INT
                        );

CREATE TABLE funcionario( id                INT
                        , descricao         VARCHAR(100)
                        , salario           DECIMAL
                        , data_admissao     DATE
                        , tipo_contrato     VARCHAR(100)
                        , super_mercado_id  INT
                        );

CREATE TABLE prestador_servicos ( id                INT
                                , descricao         VARCHAR(100)
                                , tipo_servico      VARCHAR(100)
                                , data_contrato     DATE
                                , data_renovacao    DATE
                                , data_pagamento    DATE
                                , super_mercado_id  INT
                                );

CREATE TABLE imobilizados   ( id                        INT
                            , descricao                 VARCHAR(100)
                            , valor                     DECIMAL
                            , data_aquisicao            DATE
                            , tempo_depreciacao_anos    INT
                            , super_mercado_id          INT
                        );

CREATE TABLE faturamento    ( id                INT
                            , descricao         VARCHAR(100)
                            , data_faturamento  DATE
                            , valor_faturamento DECIMAL
                            , super_mercado_id  INT
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

INSERT INTO super_mercado   ( descricao
                            , endereco   	      
					        )
                    VALUES( 'Super Mercado Melhor Preço - Filial 1'
                            ,'Av. Brasil, 1550'
                            )
                            ,( 'Super Mercado Melhor Preço - Filial 2'
                            ,'Av. Paraguai, 523'
                            )
                            ,( 'Super Mercado Melhor Preço - Filial 3'
                            ,'Av. Bolívia, 1020'
                            )
                            ,( 'Super Mercado Melhor Preço - Filial 4'
                            ,'Av. Argentina, 2302'
                            )
                            ,('Super Mercado Melhor Preço - Filial 5'
                            ,'Av. Uruguai, 750'
                            )
                            ,( 'Super Mercado Melhor Preço - Filial 6'
                            ,'Av. Chile, 23'
                            )
                            ,( 'Super Mercado Melhor Preço - Filial 7'
                            ,'Av. Panamá, 632'
                            )
                            ,( 'Super Mercado Melhor Preço - Filial 8'
                            ,'Av. Guiana Francesa, 452'
                            )
                            ,( 'Super Mercado Melhor Preço - Filial 9'
                            ,'Av. Cuba, 245'
                            )
                            ,('Super Mercado Melhor Preço - Filial 10'
                            ,'Av. Mexico, 900'
                            );

INSERT INTO funcionario     ( descricao
                            , data_admissao
                            , tipo_contrato
                            , super_mercado_id      
					        )
                    VALUES( 'Marcos Castro'
                            , STR_TO_DATE('28/08/2015', '%d/%m/%Y')
                            , 'CLT'
                            , 1
                            )
                            ,( 'Amélia Franco'
                            , STR_TO_DATE('10/02/2018', '%d/%m/%Y')
                            , 'CONTRATO 2 ANOS'
                            , 2
                            )
                            ,( 'Maurício Marcedo'
                            , STR_TO_DATE('01/03/2004', '%d/%m/%Y')
                            , 'CONTRATO 4 ANOS'
                            , 3
                            )
                            ,( 'Juliano Almeida'
                            , STR_TO_DATE('20/10/2020', '%d/%m/%Y')
                            , 'CLT'
                            , 4
                            )
                            ,('Ana Pacheco'
                            , STR_TO_DATE('15/05/2014', '%d/%m/%Y')
                            , 'CONTRATO INDEFINIDO'
                            , 5
                            )
                            ,( 'Marcos Castro'
                            , STR_TO_DATE('17/12/2012', '%d/%m/%Y')
                            , 'CLT'
                            , 6
                            )
                            ,( 'Célio Gomes'
                            , STR_TO_DATE('05/10/2020', '%d/%m/%Y')
                            , 'CLT'
                            , 7
                            )
                            ,( 'Daniela Cruz'
                            , STR_TO_DATE('08/04/2022', '%d/%m/%Y')
                            , 'CLT'
                            , 8
                            )
                            ,( 'Mara Melo'
                            , STR_TO_DATE('06/02/2003', '%d/%m/%Y')
                            , 'CONTRATO INDEFINIDO'
                            , 9
                            )
                            ,('Marcos Castro'
                            , STR_TO_DATE('09/07/2019', '%d/%m/%Y')
                            , 'CLT'
                            , 10
                            );

INSERT INTO prestador_servicos  ( descricao
                                , tipo_servico
                                , data_contrato
                                , data_renovacao
                                , data_pagamento
                                , super_mercado_id   
					            )
                    VALUES( 'Decor decoração'
                            , 'Decoração de ambientes' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 1
                            )
                            ,( 'Funilaria MAcedo'
                            , ' Serviços de funilaria' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 2
                            )
                            ,( 'Limpa Bem'
                            , 'Servoços e limpeza' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 3
                            )
                            ,( 'Segur Seguro'
                            , 'Vigilancia e segurança' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 4
                            )
                            ,('Rent a Bar'
                            ,'Aluguel de equipamentos de refrigeração' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 5
                            )
                            ,( 'Trans Marcos'
                            , 'Entregas e fretes' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 6
                            )
                            ,( 'Conta Dinheiro'
                            , 'Assessoria contábil' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 7
                            )
                            ,( 'Import Parts'
                            , 'Importadora' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 8
                            )
                            ,( 'Frete do Manuel'
                            , 'Entregas e fretes' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 9
                            )
                            ,('Pão Quentinho'
                            , 'Padaria e confeitaria' 
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , STR_TO_DATE('28/01/2023', '%d/%m/%Y')
                            , STR_TO_DATE('28/08/2022', '%d/%m/%Y')
                            , 10
                            );

INSERT INTO imobilizados   ( descricao
                            , valor
                            , data_aquisicao
                            , tempo_depreciacao_anos
                            , super_mercado_id   	      
					        )
                    VALUES( 'Refrigerador vertical'
                            , 2320.23
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , 5
                            , 1
                            )
                            ,( 'Refrigerador horizontal'
                            , 1990.89
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , 5
                            , 2
                            )
                            ,( 'Moedor de carnes'
                            , 900.56
                            , STR_TO_DATE('28/06/2021', '%d/%m/%Y')
                            , 3
                            , 3
                            )
                            ,( 'Prédio 3500M² filial 4'
                            , 2350985.60
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , 10
                            , 4
                            )
                            ,('Camimhão VW 15200 - entregas'
                            , 2320.23
                            , STR_TO_DATE('05/05/2020', '%d/%m/%Y')
                            , 8
                            , 5
                            )
                            ,( 'Veículo Corsa - anuncios'
                            , 35620.40
                            , STR_TO_DATE('28/06/2014', '%d/%m/%Y')
                            , 6
                            , 6
                            )
                            ,( 'Prédio 1500M² filial 7'
                            , 986471.20
                            , STR_TO_DATE('28/06/2014', '%d/%m/%Y')
                            , 10
                            , 7
                            )
                            ,( 'Equipamento para geração de energia solar'
                            , 20650.40
                            , STR_TO_DATE('28/06/2020', '%d/%m/%Y')
                            , 20
                            , 8
                            )
                            ,( 'Gerador a Diesel'
                            , 15652.40
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , 7
                            , 9
                            )
                            ,('Serra elétrica'
                            , 4560.85
                            , STR_TO_DATE('28/06/2022', '%d/%m/%Y')
                            , 2
                            , 10
                            );

INSERT INTO faturamento   ( descricao
                            , data_faturamento  DATE
                            , valor_faturamento DECIMAL
                            , super_mercado_id  INT   	      
					        )
                    VALUES( 'LOJA 1'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 102503.20
                            , 1
                            )
                            ,( 'LOJA 2'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 95123.69
                            , 2
                            )
                            ,( 'LOJA 3'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 25030.99
                            , 3
                            )
                            ,( 'LOJA 4'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 45685.80
                            , 4
                            )
                            ,('LOJA 5'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 76541.74
                            , 5
                            )
                            ,( 'LOJA 6'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 150654.63
                            , 6
                            )
                            ,( 'LOJA 7'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 22485.23
                            , 7
                            )
                            ,( 'LOJA 8'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 50412.56
                            , 8
                            )
                            ,( 'LOJA 9'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 32562.26
                            , 9
                            )
                            ,('LOJA 10'
                            , STR_TO_DATE('01/09/2022', '%d/%m/%Y')
                            , 86547.98
                            , 10
                            );
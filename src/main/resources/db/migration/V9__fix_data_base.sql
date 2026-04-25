
INSERT INTO user_account (user_name, user_password, permission) VALUES
                                                                    ('admin', '1234', 'GERENTE'),
                                                                    ('funcionario', '1234', 'FUNCIONARIO');


INSERT INTO funcionario (id,name, cpf) VALUES
                                        (1,'Ricardo Silva', '10020030044'),
                                        (2,'Beatriz Souza', '50060070088');


INSERT INTO languages_funcionario (fk_funcionario_id, language) VALUES
                                                                    (1, 'PORTUGUESE'),
                                                                    (1, 'ENGLISH'),
                                                                    (2, 'SPANISH');


INSERT INTO clientes (id,cnpj, cpf, name, country_of_customer) VALUES
                                                                (1,NULL, '99988877766', 'John Doe', 'UNITED_STATES'),
                                                                (2,'98765432000100', NULL, 'Importadora del Este', 'PARAGUAI'),
                                                                (3,NULL, '12312312312', 'Ana Oliveira', 'BRAZIL');


INSERT INTO clientes_languages (fk_clientes_id, language) VALUES
                                                              (1, 'ENGLISH'),
                                                              (2, 'SPANISH'),
                                                              (3, 'PORTUGUESE');


INSERT INTO passeio (id,price, durations_in_minute, country_of_tour, km_of_tour, name, locations) VALUES
                                                                                                   (1,450.00, 240, 'BRAZIL', 10, 'Helicopter Ride Falls', 'Foz do Iguaçu'),
                                                                                                   (2,120.00, 90, 'PARAGUAY', 5, 'Shopping Tour del Este', 'Ciudad del Este');


INSERT INTO reservations (date, fk_passeio_id, fk_funcionario_id, fk_clientes_id, value, status) VALUES
                                                                                                     ('2026-05-10', 1, 1, 1, 450.00, 'pendente'),
                                                                                                     ('2026-05-12', 2, 2, 2, 120.00, 'pendente');


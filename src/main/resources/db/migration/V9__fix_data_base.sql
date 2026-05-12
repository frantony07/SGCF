
INSERT INTO user_account (user_name, user_password, permission,user_email) VALUES
    ('admin', '1234', 'GERENTE','frantonynieves@gmail.com'),
    ('funcionario', '1234', 'FUNCIONARIO','victor.de.souza.muller@gmail.com');


INSERT INTO funcionario (name, cpf) VALUES
    ('Ricardo Silva', '10020030044'),
    ('Beatriz Souza', '50060070088');


INSERT INTO languages_funcionario (fk_funcionario_id, language) VALUES
    (1, 'PORTUGUESE'),
    (1, 'ENGLISH'),
    (2, 'SPANISH');


INSERT INTO clientes (cnpj, cpf, name, country_of_customer) VALUES
    (NULL, '99988877766', 'John Doe', 'UNITED_STATES'),
    ('98765432000100', NULL, 'Importadora del Este', 'PARAGUAI'),
    (NULL, '12312312312', 'Ana Oliveira', 'BRAZIL');


INSERT INTO clientes_languages (fk_clientes_id, language) VALUES
    (1, 'ENGLISH'),
    (2, 'SPANISH'),
    (3, 'PORTUGUESE');


INSERT INTO passeio (price, durations_in_minute, country_of_tour, km_of_tour, name, locations) VALUES
    (450.00, 240, 'BRAZIL', 10, 'Helicopter Ride Falls', 'Foz do Iguaçu'),
    (120.00, 90, 'PARAGUAY', 5, 'Shopping Tour del Este', 'Ciudad del Este');


INSERT INTO reservations (date, fk_passeio_id, fk_funcionario_id, fk_clientes_id, value, status) VALUES
    ('2026-05-10', 1, 1, 1, 450.00, 'pendente'),
    ('2026-05-12', 2, 2, 2, 120.00, 'pendente');


INSERT INTO usuario (login, senha)
VALUES ('op1', 'op1'), ('op2', 'op2');

INSERT INTO produto (nome, quantidade, precoVenda)
VALUES ('Banana', 100, 5.00), ('Laranja', 500, 2.00), ('Manga', 800, 4.00);

INSERT INTO pessoa (nome, endereco, cidade, estado, telefone, email)
VALUES
('Pedro Cardoso', 'Avenida Brasil', 'Rio de Janeiro', 'RJ', '21988887777', 'pedro.cardoso@email.com'),
('Fernanda Souza', 'Travessa Joaquim', 'Salvador', 'BA', '71977776666', 'fernanda.souza@email.com'),
('COMPANY', 'Rua das Laranjeiras', 'Curitiba', 'PR', '41966665555', 'company@email.com');

INSERT INTO pessoa_fisica (id_pessoa, cpf)
VALUES (1, '11122233344'), (2, '44433322211');

INSERT INTO pessoa_juridica (id_pessoa, cnpj)
VALUES (3, '11223344000155');

INSERT INTO movimento (id_pessoa, id_produto, id_usuario, quantidade, tipo, valor_unitario)
VALUES
(1, 1, 1, 10, 'E', 15.50), 
(1, 2, 1, 5, 'S', 45.00);

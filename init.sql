
create database if not exists BD1_APS;
USE BD1_APS;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Venda;
DROP TABLE IF EXISTS ProdutoFornecido;
DROP TABLE IF EXISTS Produto;
DROP TABLE IF EXISTS Funcionario;
DROP TABLE IF EXISTS Fornecedor;
DROP TABLE IF EXISTS Filial;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Entidade;
DROP TABLE IF EXISTS Categoria;
DROP TABLE IF EXISTS Endereco;

####################################

CREATE TABLE Categoria (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	nome TEXT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Endereco (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	logradouro TEXT NOT NULL,
	bairro TEXT NOT NULL,
	cidade TEXT NOT NULL,
	estado CHAR(2) NOT NULL,
	pais CHAR(3) NOT NULL,
	PRIMARY KEY (id)
);

####################################

CREATE TABLE Entidade (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	nome TEXT NOT NULL,
	endereco BIGINT UNSIGNED NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	FOREIGN KEY (endereco) REFERENCES Endereco(id)
);

CREATE TABLE Cliente (
	id BIGINT UNSIGNED NOT NULL,
	dataNasc CHAR(8) NOT NULL,
	FOREIGN KEY (id) REFERENCES Entidade(id)
);

CREATE TABLE Filial (
	id BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (id) REFERENCES Entidade(id)
);

CREATE TABLE Fornecedor (
	id BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (id) REFERENCES Entidade(id)
);

CREATE TABLE Funcionario (
	id BIGINT UNSIGNED NOT NULL,
	salario DOUBLE UNSIGNED NOT NULL,
	FOREIGN KEY (id) REFERENCES Entidade(id)
);

####################################

CREATE TABLE Produto (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	nome TEXT NOT NULL,
	categoria BIGINT UNSIGNED NOT NULL,
	preco DOUBLE NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (categoria) REFERENCES Categoria(id)
);

CREATE TABLE ProdutoFornecido (
	produto BIGINT UNSIGNED NOT NULL,
	fornecedor BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (produto) REFERENCES Produto(id),
	FOREIGN KEY (fornecedor) REFERENCES Fornecedor(id)
);

CREATE TABLE Venda (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	cliente BIGINT UNSIGNED NOT NULL,
	filial BIGINT UNSIGNED NOT NULL,
	funcionario BIGINT UNSIGNED NOT NULL,
	data CHAR(8) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (cliente) REFERENCES Cliente(id),
	FOREIGN KEY (filial) REFERENCES Filial(id),
	FOREIGN KEY (funcionario) REFERENCES Funcionario(id)
);

CREATE TABLE Item (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	venda BIGINT UNSIGNED NOT NULL,
	produto BIGINT UNSIGNED NOT NULL,
	desconto DOUBLE /* NULLABLE */,
	quantidade INT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (venda) REFERENCES Venda(id),
	FOREIGN KEY (produto) REFERENCES Produto(id)
);

####################################

INSERT INTO Categoria (id, nome) VALUES (1, 'Limpeza');
INSERT INTO Categoria (id, nome) VALUES (2, 'Alimentos Básicos');
INSERT INTO Categoria (id, nome) VALUES (3, 'Feira');
INSERT INTO Categoria (id, nome) VALUES (4, 'Carnes, Aves e Peixes');
INSERT INTO Categoria (id, nome) VALUES (5, 'Bebidas');
INSERT INTO Categoria (id, nome) VALUES (6, 'Bedidas Alcoólicas');
INSERT INTO Categoria (id, nome) VALUES (7, 'Higiene e Cuidados Pessoais');
INSERT INTO Categoria (id, nome) VALUES (8, 'Biscoitos e Salgadinhos');
INSERT INTO Categoria (id, nome) VALUES (9, 'Drogas');

####################################

INSERT INTO Endereco (id, logradouro, bairro, cidade, estado, pais)
	VALUES (1, 'Rua Avenilo Piacentini, 90', 'Jardim Bandeirantes', 'Campo Mourão', 'PR', 'BRA');
INSERT INTO Entidade (id, nome, endereco, telefone)
	VALUES (1, 'Mercadinho "Totalmente não é o Paraná" Família', 1, '4435180500');
INSERT INTO Filial (id) VALUES (1);

####################################

INSERT INTO Endereco (id, logradouro, bairro, cidade, estado, pais)
	VALUES (2, 'Avenida Irmãos Pereira, 1500', 'Centro', 'Campo Mourão', 'PR', 'BRA');
INSERT INTO Entidade (id, nome, endereco, telefone)
	VALUES (2, 'Mercado "Não é o Paraná, juro" Max', 2, '4435182600');
INSERT INTO Filial (id) VALUES (2);

####################################

INSERT INTO Endereco (id, logradouro, bairro, cidade, estado, pais)
	VALUES (3, 'Rua José Custódio de Oliveira, 1564', 'Centro', 'Campo Mourão', 'PR', 'BRA');
INSERT INTO Entidade (id, nome, endereco, telefone)
	VALUES (3, 'Drogas Vinicin', 3, '4469696969');
INSERT INTO Fornecedor (id) VALUES (3);

####################################

INSERT INTO Endereco (id, logradouro, bairro, cidade, estado, pais)
	VALUES (4, 'Rua São Paulo, S/n', 'Praça do Centro', 'Campo Mourão', 'PR', 'BRA');
INSERT INTO Entidade (id, nome, endereco, telefone)
	VALUES (4, 'Mendigo da Cachaça', 4, '4598989898');
INSERT INTO Fornecedor (id) VALUES (4);

####################################

INSERT INTO Endereco (id, logradouro, bairro, cidade, estado, pais)
	VALUES (5, 'Avenina JK', 'Centro', 'Campo Mourão', 'PR', 'BRA');
INSERT INTO Entidade (id, nome, endereco, telefone)
	VALUES (5, 'Neusa', 5, '4435262020');
INSERT INTO funcionario (id, salario) VALUES (5, 150.00);

####################################

INSERT INTO Endereco (id, logradouro, bairro, cidade, estado, pais)
	VALUES (6, 'Rua Pitanga', 'Centro', 'Campo Mourão', 'PR', 'BRA');
INSERT INTO Entidade (id, nome, endereco, telefone)
	VALUES (6, 'Senhor Dono', 6, '4450550555');
INSERT INTO funcionario (id, salario) VALUES (6, 5800.00);

####################################

INSERT INTO Endereco (id, logradouro, bairro, cidade, estado, pais)
	VALUES (7, 'Avenida José Custódio de Oliveira', 'Centro', 'Campo Mourão', 'PR', 'BRA');
INSERT INTO Entidade (id, nome, endereco, telefone)
	VALUES (7, 'Henriko Alberton', 7, '44933000582');
INSERT INTO Cliente (id, dataNasc) VALUES (7, '20041998');

####################################

INSERT INTO Endereco (id, logradouro, bairro, cidade, estado, pais)
	VALUES (8, 'Rua Pitanga, 42', 'Centro', 'Campo Mourão', 'PR', 'BRA');
INSERT INTO Entidade (id, nome, endereco, telefone)
	VALUES (8, 'Gueremias', 8, '4598502691');
INSERT INTO Cliente (id, dataNasc) VALUES (8, '25121998');

####################################

INSERT INTO Produto (id, nome, categoria, preco)
	VALUES (1, 'Alcool da Ilha', 1, 4.99);
INSERT INTO Produto (id, nome, categoria, preco)
	VALUES (2, 'Neosoro Solução Nasal', 9, 3.99);

####################################

INSERT INTO ProdutoFornecido (produto, fornecedor) VALUES (1, 3);
INSERT INTO ProdutoFornecido (produto, fornecedor) VALUES (1, 4);
INSERT INTO ProdutoFornecido (produto, fornecedor) VALUES (2, 4);

####################################

INSERT INTO Venda (id, cliente, filial, funcionario, data)
	VALUES (1, 7, 2, 5, '02112018');
INSERT INTO Item (id, venda, produto, desconto, quantidade)
	VALUES (1, 1, 1, 0.0, 3);
INSERT INTO Item (id, venda, produto, desconto, quantidade)
	VALUES (2, 1, 2, 0.0, 1);
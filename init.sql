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

####################################


CREATE TABLE Cliente (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	nome TEXT NOT NULL,
	logradouro TEXT NOT NULL,
	bairro TEXT NOT NULL,
	cidade TEXT NOT NULL,
	estado CHAR(2) NOT NULL,
	pais CHAR(3) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	dataNasc CHAR(8) NOT NULL
	
);

CREATE TABLE Filial (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	nome TEXT NOT NULL,
	logradouro TEXT NOT NULL,
	bairro TEXT NOT NULL,
	cidade TEXT NOT NULL,
	estado CHAR(2) NOT NULL,
	pais CHAR(3) NOT NULL,
	telefone VARCHAR(15) NOT NULL
);

CREATE TABLE Fornecedor (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	nome TEXT NOT NULL,
	logradouro TEXT NOT NULL,
	bairro TEXT NOT NULL,
	cidade TEXT NOT NULL,
	estado CHAR(2) NOT NULL,
	pais CHAR(3) NOT NULL,
	telefone VARCHAR(15) NOT NULL
);

CREATE TABLE Funcionario (
	id SERIAL, -- alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	nome TEXT NOT NULL,
	logradouro TEXT NOT NULL,
	bairro TEXT NOT NULL,
	cidade TEXT NOT NULL,
	estado CHAR(2) NOT NULL,
	pais CHAR(3) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
   	salario DOUBLE UNSIGNED NOT NULL
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

INSERT INTO Filial (id, logradouro, bairro, cidade, estado, pais, nome, telefone) VALUES (1, 'Rua Avenilo Piacentini, 90', 'Jardim Bandeirantes', 'Campo Mourão', 'PR', 'BRA', 'Mercadinho "Totalmente não é o Paraná" Família', '4435180500');

####################################

INSERT INTO Filial (id, logradouro, bairro, cidade, estado, pais, nome, telefone) VALUES (2, 'Avenida Irmãos Pereira, 1500', 'Centro', 'Campo Mourão', 'PR', 'BRA', 'Mercado "Não é o Paraná, juro" Max', '4435182600');

####################################

INSERT INTO Fornecedor (id, logradouro, bairro, cidade, estado, pais, nome, telefone) VALUES (3, 'Rua José Custódio de Oliveira, 1564', 'Centro', 'Campo Mourão', 'PR', 'BRA', 'Drogas Vinicin', '4469696969');

####################################

INSERT INTO Fornecedor (id, logradouro, bairro, cidade, estado, pais, nome, telefone) VALUES (4, 'Rua São Paulo, S/n', 'Praça do Centro', 'Campo Mourão', 'PR', 'BRA', 'Mendigo da Cachaça', '4598989898');

####################################

INSERT INTO Funcionario (id, logradouro, bairro, cidade, estado, pais, nome, telefone, salario) VALUES (6, 'Rua Pitanga', 'Centro', 'Campo Mourão', 'PR', 'BRA', 'Senhor Dono', '4450550555', 5800.00);

####################################

INSERT INTO Cliente (id, logradouro, bairro, cidade, estado, pais, nome, telefone, dataNasc) VALUES (7, 'Avenida José Custódio de Oliveira', 'Centro', 'Campo Mourão', 'PR', 'BRA',  'Henriko Alberton', '44933000582', '20041998');

####################################

INSERT INTO Cliente (id, logradouro, bairro, cidade, estado, pais, nome, telefone, dataNasc) VALUES (8, 'Rua Pitanga, 42', 'Centro', 'Campo Mourão', 'PR', 'BRA', 'Gueremias', '4598502691', '25121998');

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
	VALUES (1, 7, 2, 6, '02112018');
INSERT INTO Item (id, venda, produto, desconto, quantidade)
	VALUES (1, 1, 1, 0.0, 3);
INSERT INTO Item (id, venda, produto, desconto, quantidade)
	VALUES (2, 1, 2, 0.0, 1);
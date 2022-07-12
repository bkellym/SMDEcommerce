CREATE TABLE IF NOT EXISTS categoria (
   	id serial PRIMARY KEY,
	descricao VARCHAR(255) not null
);

CREATE TABLE IF NOT EXISTS usuario (
   	id serial UNIQUE NOT NULL ,
	nome VARCHAR(255) NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL,
   	login VARCHAR(50) UNIQUE NOT NULL,
	senha VARCHAR (50) NOT NULL,
	admins int default 0 not null,
	endereco VARCHAR(255) null,
	primary key (id, login, email)
);

CREATE TABLE IF NOT EXISTS produto (
   	id serial PRIMARY KEY,
	descricao VARCHAR(255) not null,
	foto VARCHAR(255) not null,
	valor real not null,
	quantidade int not null,
	id_categoria serial,
	foreign key (id_categoria) references categoria (id)
);

CREATE TABLE IF NOT EXISTS venda (
	id serial PRIMARY KEY,
	data_hora Timestamp without Time Zone,
	id_usuario serial,
	foreign key (id_usuario) references usuario (id)
);

CREATE TABLE IF NOT EXISTS produto_carrinho (
	id serial PRIMARY KEY,
	quantidade int not null,
	id_usuario serial,
	id_produto serial,
	foreign key (id_produto) references produto (id),
	foreign key (id_usuario) references usuario (id)
);

CREATE TABLE IF NOT EXISTS produto_venda (
	id serial PRIMARY KEY,
	valor real not null,
	quantidade int not null,
	id_produto serial,
	id_venda serial,
	foreign key (id_produto) references produto (id),
	foreign key (id_venda) references venda (id)
);

insert into categoria (id, descricao) values 
(1,	'perifericos'),
(2,	'Acessórios');

insert into produto (id, descricao, foto, valor, quantidade, id_categoria) values 
(1,	'Capa Melancia p/ Notebook',	'https://images.tcdn.com.br/img/img_prod/702466/case_notebook_caroco_de_melancia_15_6_1485_1_164fcd6fb57cb0b3f2d056074ed7638c.jpg',	15.9, 10, 2),
(2, 'Teclado HyperX', 'https://m.media-amazon.com/images/I/51-XotrdbXL._AC_SY450_.jpg', 50.9, 15, 1),
(3, 'Teclado GoldenTech', 'https://a-static.mlcdn.com.br/1500x1500/teclado-usb-goldentec-gt-tgaming-preto-vermelho-goldentec-acessorios/support/4ce8bb34c5fa11eb8ff64201ac18500e/1e900330ce0919fa09b2031ac2c77aed.jpg', 35.9, 18, 1);



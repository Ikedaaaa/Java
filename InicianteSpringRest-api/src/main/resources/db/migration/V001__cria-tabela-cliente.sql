create table Clientes (
	idcliente bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(60) not null,
    telefone varchar(60) not null,
    
    primary key (idcliente)
);
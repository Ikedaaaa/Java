create table Clientes (
	idCliente bigint not null auto_increment,
    nome varchar (255) not null,
    email varchar (255) not null,
    telefone varchar (255) not null,
    
    primary key (idCliente)
);
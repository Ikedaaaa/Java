create table Entregas (
	identrega bigint not null auto_increment,
	idcliente bigint not null,
    taxa decimal(10,2) not null,
    status varchar(20) not null,
    data_pedido datetime not null,
    data_finalizacao datetime,
    
    destinatario_nome varchar(60) not null,
    destinatario_logradouro varchar(255) not null,
    destinatario_numero varchar(30) not null,
    destinatario_complemento varchar(60) not null,
    destinatario_bairro varchar(30) not null,
    
    primary key (identrega)
);

alter table Entregas add constraint fk_entrega_cliente
foreign key (idcliente) references Clientes (idcliente)
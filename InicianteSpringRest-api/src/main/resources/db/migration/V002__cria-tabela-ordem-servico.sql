create table Ordens_Servico (
	idordem_servico bigint not null auto_increment,
    cliente_id bigint not null,
    descricao text not null,
    preco decimal(10,2) not null,
    status varchar(20) not null,
    data_abertura datetime not null,
    data_finalizacao datetime,
    
    primary key (idordem_servico)
);

alter table Ordens_Servico add constraint fk_ordem_servico_cliente
foreign key (cliente_id) references Clientes (idcliente)
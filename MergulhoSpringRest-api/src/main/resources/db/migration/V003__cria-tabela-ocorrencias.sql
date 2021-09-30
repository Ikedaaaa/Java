create table Ocorrencias (
	idocorrencia bigint not null auto_increment,
	entrega_id bigint not null,
	descricao text not null,
	data_registro datetime not null,
	
	primary key (idocorrencia)
);

alter table Ocorrencias add constraint fk_ocorrencia_entrega
foreign key (entrega_id) references entregas (identrega)
create table Comentarios (
	idcomentario bigint not null auto_increment,
    ordem_servico_id bigint not null,
    descricao text not null,
    data_envio datetime not null,
    
    primary key (idcomentario)
);

alter table Comentarios add constraint fk_comentario_ordem_servico
foreign key (ordem_servico_id) references Ordens_Servico (idordem_servico)
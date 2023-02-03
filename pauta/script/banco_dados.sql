CREATE SEQUENCE sq_voto start with 1 increment by 1 NO MINVALUE NO MAXVALUE CACHE 1;
create table voto(
  id 		 bigint not null DEFAULT nextval('sq_voto'::regclass) constraint voto_pkey primary key,
  voto  	 boolean,
  pauta  	 bigint constraint pauta_id references pauta(id),
  associado  varchar constraint associado_id references associado(cpf)
);

create table associado(
  cpf 	varchar(11)  not null constraint cpf_pkey primary key,
  nome  varchar(80)
);

CREATE SEQUENCE sq_pauta start with 1 increment by 1 NO MINVALUE NO MAXVALUE CACHE 1;
create table pauta(
  id 			bigint not null DEFAULT  nextval('sq_pauta'::regclass) constraint avaliacao_pkey primary key,
  nome 			varchar(80),
  descricao     varchar(200),
  dataInicio	timestamp
);

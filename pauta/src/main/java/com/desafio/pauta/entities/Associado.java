package com.desafio.pauta.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "associado", schema = "public")
public class Associado {

	@Id
	@Column(name = "cpf")
	private String cpf;

	@Column(name = "nome")
	private String nome;


}

package com.desafio.pauta.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pauta", schema = "public")
public class Pauta implements Serializable {

	public Pauta (Long id){
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "sq_pauta", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sq_pauta", sequenceName = "sq_pauta", allocationSize = 1)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "dataInicio")
	private Date data;


}

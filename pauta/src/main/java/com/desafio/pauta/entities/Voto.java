package com.desafio.pauta.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voto", schema = "public")
public class Voto {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "sq_voto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sq_voto", sequenceName = "sq_voto", allocationSize = 1)
	private Long id;

	@Column(name = "voto")
	private boolean voto;

	@ManyToOne
	@JoinColumn(name = "associado", referencedColumnName = "cpf")
	private Associado associado;

	@ManyToOne
	@JoinColumn(name= "pauta", referencedColumnName = "id")
	private Pauta pauta;

}

package com.desafio.pauta.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class StandardError implements Serializable {

	private Instant timeStamp;
	private Integer statusCode;
	private String response;
	private String error;
	private String path;
}

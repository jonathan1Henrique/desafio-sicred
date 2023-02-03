package com.desafio.pauta.controllers;

import com.desafio.pauta.entities.Pauta;
import com.desafio.pauta.resources.dtos.InicioDTO;
import com.desafio.pauta.resources.dtos.PautaDTO;
import com.desafio.pauta.resources.dtos.ResponseDTO;
import com.desafio.pauta.services.IPautaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private IPautaService service;

	@PostMapping("/v1")
	@ApiOperation(value = "Serviço responsável por criar uma pauta")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created", response = Pauta.class)})
	public ResponseEntity create(@Valid @RequestBody PautaDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@PutMapping({"/v1/inicia/{idPauta}"})
	@ApiOperation(value = "Serviço responsável por dar início a votação da pauta")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseDTO.class)})
	public ResponseEntity start(@PathVariable Long idPauta ,@RequestBody InicioDTO dto ){
		return ResponseEntity.status(HttpStatus.OK).body(service.start(idPauta, dto.getDataInicio()));
	}

	@GetMapping("/v1")
	@ApiOperation(value = "Serviço responsável por busca paginada de todas as pautas")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Page.class)})
	public ResponseEntity findAll(@PageableDefault Pageable page){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(page));
	}
}

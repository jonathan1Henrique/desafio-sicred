package com.desafio.pauta.controllers;

import com.desafio.pauta.entities.Associado;
import com.desafio.pauta.resources.dtos.AssociadoDTO;
import com.desafio.pauta.services.IAssociadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    @Autowired
    private IAssociadoService service;

    @PostMapping("/v1")
    @ApiOperation(value = "Serviço responsável cadastrar um associado")
    @ApiResponses({ @ApiResponse(code = 201, message = "Created", response = Associado.class)})
    public ResponseEntity create(@Valid @RequestBody AssociadoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
}

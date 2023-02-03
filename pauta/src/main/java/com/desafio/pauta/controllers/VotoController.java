package com.desafio.pauta.controllers;


import com.desafio.pauta.resources.dtos.ResultadoVotoDTO;
import com.desafio.pauta.resources.dtos.VotoDTO;
import com.desafio.pauta.services.IVotoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private IVotoService service;

    @PostMapping("/v1")
    @ApiOperation(value = "Serviço responsável por receber os votos e enviar para o Kafka")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = VotoDTO.class)})
    public ResponseEntity votar(@Valid @RequestBody VotoDTO voto){

        service.votar(voto);

        return ResponseEntity.status(HttpStatus.OK).body("seu voto foi enviado: " + voto.tranfomeJson());
    }

    @GetMapping("/v1/resultado/{idPauta}")
    @ApiOperation(value = "Serviço responsável por contabilizar os votos")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResultadoVotoDTO.class)})
    public ResponseEntity resultado(@PathVariable Long idPauta){

        return ResponseEntity.status(HttpStatus.OK).body(service.resultado(idPauta));
    }
}
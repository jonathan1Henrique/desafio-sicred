package com.desafio.pauta.resources.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String response;

    public ResponseDTO responseCreatePauta(String nome, Date data){
        return new ResponseDTO("A pauta " + nome +
                " foi aberta com sucesso, " +
                "a votação ficará aberta até " + data);
    }

}

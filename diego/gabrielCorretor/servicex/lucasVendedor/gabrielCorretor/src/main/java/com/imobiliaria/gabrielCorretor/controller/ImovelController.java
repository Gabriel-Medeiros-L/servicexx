package com.imobiliaria.gabrielCorretor.controller;


import com.concessionaria.lucasVendedor.domain.Carros;
import com.concessionaria.lucasVendedor.domain.Client;
import com.concessionaria.lucasVendedor.dtos.CarrosDto;
import com.concessionaria.lucasVendedor.service.carrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imovel")
public class ImovelController {


    @Autowired
    private imoveisService imoveisService;

    @GetMapping
    public ResponseEntity<List<ImoveisDto>> findAll(){
        List<Imoveis> imoveis = imoveisService.getAllProperties();
        List<ImoveisDto> imoveistDTOS = imoveis.stream()
                .map(ImoveisDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(imoveistDTOS);
    }

    @PostMapping
    public ResponseEntity<Imoveis> save(@RequestBody ImoveisDto imoveisDto) {
        Imoveis imoveis = imoveisService.create(imoveisDto);
        return new ResponseEntity<>(imoveis, HttpStatus.CREATED);
    }
}

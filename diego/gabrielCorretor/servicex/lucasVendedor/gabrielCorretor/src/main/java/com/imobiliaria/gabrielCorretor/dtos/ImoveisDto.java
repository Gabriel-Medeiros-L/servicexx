package com.imobiliaria.gabrielCorretor.dtos;

import com.concessionaria.lucasVendedor.domain.Carros;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ImoveisDto {

    private Long id;

    private String nome;

    private String modelo;

    private List<Long> clienteIds;

    public ImoveisDto(Imoveis imoveis) {
        this.id = imoveis.getId();
        this.nome = imoveis.getNome();
        this.modelo = imoveis.getModelo();
        if (imoveis.getClients() != null) {
            this.clienteIds = imoveis.getClients().stream()
                    .map(cliente -> cliente.getId())
                    .collect(Collectors.toList());
        }
    }
}

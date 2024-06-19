package com.imobiliaria.gabrielCorretor.domain;

import com.concessionaria.lucasVendedor.dtos.CarrosDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Imoveis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String modelo;

    @OneToMany(mappedBy = "imovelEscolhido", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();

    public Imoveis(ImoveisDto imoveisDto) {
        super();
        this.id = imoveisDto.getId();
        this.nome = imoveisDto.getNome();
        this.modelo = imoveisDto.getModelo();
    }
}

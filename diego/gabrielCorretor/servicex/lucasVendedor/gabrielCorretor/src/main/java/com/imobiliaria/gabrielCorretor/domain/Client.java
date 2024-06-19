package com.imobiliaria.gabrielCorretor.domain;

import com.concessionaria.lucasVendedor.dtos.ClientDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String contato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imovel_id")
    @JsonIgnoreProperties("clients")
    private Imoveis imovelEscolhido;


    public Client(ClientDto clientDto) {
        this.nome = clientDto.getNome();
        this.contato = clientDto.getContato();
        if (clientDto.getImovelId() != null) {
            this.imovelEscolhido = new Imoveis();
            this.imovelEscolhido.setId(clientDto.getImovelId());
        }
    }
}

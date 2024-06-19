package com.imobiliaria.gabrielCorretor.dtos;


import com.concessionaria.lucasVendedor.domain.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDto {

    private  Long id;

    private  String nome;

    private  String contato;

    private Long imovelId;


    public ClientDto(Client client) {
        this.id = client.getId();
        this.nome = client.getNome();
        this.contato = client.getContato();
        if (client.getImovelEscolhido() != null) {
            this.imovelId = client.getImovelEscolhido().getId();
        }
    }
}

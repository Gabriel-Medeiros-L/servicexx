package com.imobiliaria.gabrielCorretor.controller;


import com.concessionaria.lucasVendedor.domain.Carros;
import com.concessionaria.lucasVendedor.domain.Client;
import com.concessionaria.lucasVendedor.dtos.CarrosDto;
import com.concessionaria.lucasVendedor.dtos.ClientDto;
import com.concessionaria.lucasVendedor.service.ClientService;
import com.concessionaria.lucasVendedor.service.carrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @Autowired
    private imoveisService imoveisService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        List<Client> clients = clientService.findAll();
        List<ClientDto> clientDTOS = clients.stream()
                .map(ClientDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDTOS);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado com o ID: " + id);
        }
        return ResponseEntity.ok().body(new ClientDto(client));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientDto clientDto) {
        Client client = clientService.create(clientDto);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        Client clientToUpdate = clientService.findById(id);
        if (clientToUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado com o ID: " + id);
        }
       Imoveis imoveis = imoveisService.findById(clientDto.getImovelId());
        if (clientDto.getImovelId() != null || imoveis == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Imovel não encontrado com o ID: " + clientDto.getImovelId());
        }
        Client updatedClient = new Client(clientDto);
        updatedClient.setId(clientToUpdate.getId());
        clientService.update(updatedClient);
        return ResponseEntity.ok().body("Cliente atualizado com sucesso");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Client clientToDelete = clientService.findById(id);
        if (clientToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado com o ID: " + id);
        }
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente removido com sucesso");
    }
}

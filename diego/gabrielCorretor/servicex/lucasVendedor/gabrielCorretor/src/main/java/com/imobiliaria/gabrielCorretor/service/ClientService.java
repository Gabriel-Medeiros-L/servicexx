package com.imobiliaria.gabrielCorretor.service;

import com.concessionaria.lucasVendedor.domain.Carros;
import com.concessionaria.lucasVendedor.domain.Client;
import com.concessionaria.lucasVendedor.dtos.ClientDto;
import com.concessionaria.lucasVendedor.repository.CarroRepository;
import com.concessionaria.lucasVendedor.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    public Client findById(Long id) {
        Optional<Client> client = clienteRepository.findById(id);
        return client.orElse(null); // Retorna null se não encontrar o cliente
    }

    public List<Client> findAll() {
        return clienteRepository.findAll();
    }

    public Client create(ClientDto clientDto) {
        clientDto.setId(null);
        Imoveis imoveis = imovelRepository.findById(clientDto.getImovelId())
                .orElseThrow(() -> new RuntimeException("nome do Imovel com ID " + clientDto.getImovelId() + " não encontrado."));
        Client client = new Client(clientDto);
        if (imoveis != null) {
            client.setModeloEscolhido(imoveis);
        }
        return clienteRepository.save(client);
    }

    @Transactional
    public Client update(Client client) {
        return clienteRepository.save(client);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}


package com.imobiliaria.gabrielCorretor.service;

import com.concessionaria.lucasVendedor.domain.Carros;
import com.concessionaria.lucasVendedor.dtos.CarrosDto;
import com.concessionaria.lucasVendedor.repository.ClienteRepository;
import com.concessionaria.lucasVendedor.repository.CarroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class imoveisService {

    @Autowired
    private ImoveisRepository ImovelRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Imoveis> getAllProperties() {
        return ImovelRepository.findAll();
    }

    public Imoveis findById(Long id) {
        Optional<Imoveis> optionalPropertie = ImovelRepository.findById(id);
        return optionalPropertie.orElse(null);
    }

    public Imoveis create(ImoveisDto imoveisDto) {
        imoveisDto.setId(null);
        Imoveis imoveis = new Imoveis(imoveisDto);
        return ImovelRepository.save(imoveis);
    }

    public Imoveis updatePropertie(Long id, Imoveis imoveis) {
        Optional<Imoveis> optionalPropertie = ImovelRepository.findById(id);
        if (optionalPropertie.isPresent()) {
            Imoveis imoveisExistentes = optionalPropertie.get();
            BeanUtils.copyProperties(imoveis, imoveisExistentes);
            return ImoveisRepository.save(imoveisExistentes);
        } else {
            return null;
        }
    }

    public boolean deletePropertie(Long id) {
        Optional<Imoveis> optionalPropertie = ImovelRepository.findById(id);
        if (optionalPropertie.isPresent()) {
            ImovelRepository.delete(optionalPropertie.get());
            return true;
        } else {
            return false;
        }
    }
}

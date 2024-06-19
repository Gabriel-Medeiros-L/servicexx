package com.imobiliaria.gabrielCorretor.repository;

import com.concessionaria.lucasVendedor.domain.Carros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imoveis,Long> {
}

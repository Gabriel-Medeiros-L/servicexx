package com.imobiliaria.gabrielCorretor.repository;

import com.concessionaria.lucasVendedor.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Client,Long> {
}

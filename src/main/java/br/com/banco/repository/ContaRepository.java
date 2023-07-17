package br.com.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banco.entity.Conta;

import java.util.Optional;



@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByNomeResponsavel(String nomeResponsavel);
}

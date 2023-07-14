package br.com.banco.transferencia;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;

import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
    
    List<Transferencia> findByContaId(Integer contaId);
    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);
    Page<Transferencia> findAll(Pageable pageable);
    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia BETWEEN :inicio AND :fim")
    List<Transferencia> findAllByDataTransferenciaBetween(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);
}



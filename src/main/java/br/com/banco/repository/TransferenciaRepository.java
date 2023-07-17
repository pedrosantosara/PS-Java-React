package br.com.banco.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.banco.entity.Transferencia;

import org.springframework.data.domain.Page;

import java.sql.Timestamp;

import java.util.List;

@Repository
public interface TransferenciaRepository extends PagingAndSortingRepository<Transferencia, Long> {
    
    Page<Transferencia> findByContaId(Long contaId, Pageable pageable);
    List<Transferencia> findByContaIdAndContaNomeResponsavel(Long contaId, String nomeResponsavel);
    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);
    Page<Transferencia> findAll(Pageable pageable);
    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia BETWEEN :inicio AND :fim")
    List<Transferencia> findAllByDataTransferenciaBetween(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);
    Page<Transferencia> findByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
    Long contaId, String nomeOperadorTransacao, Timestamp inicio, Timestamp fim, Pageable pageable);
    Page<Transferencia> findByContaIdAndDataTransferenciaBetween(Long contaId, Timestamp inicio, Timestamp fim, Pageable pageable);
    Page<Transferencia> findByContaIdAndNomeOperadorTransacao(Long contaId, String nomeOperadorTransacao, Pageable pageable);

}


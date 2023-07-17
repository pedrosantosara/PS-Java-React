package br.com.banco.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.banco.entity.Transferencia;
import br.com.banco.repository.TransferenciaRepository;

@Service
public class ParamsService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    
    public Page<Transferencia> filtrarTransferencias(
        String nomeOperadorTransacao,
        LocalDate inicio,
        LocalDate fim,
        Pageable pageable
        ) 
    {

        Page<Transferencia> transferenciasPage;

        if (nomeOperadorTransacao != null) {
            List<Transferencia> transferenciasList = transferenciaRepository
                    .findByNomeOperadorTransacao(nomeOperadorTransacao);
            transferenciasPage = new PageImpl<>(transferenciasList, pageable, transferenciasList.size());
        } else if (inicio != null && fim != null) {
            LocalDateTime inicioDateTime = inicio.atStartOfDay(); // Inicio
            LocalDateTime fimDateTime = fim.atTime(23, 59, 59); // Fim do dia
            Timestamp inicioTimestamp = Timestamp.valueOf(inicioDateTime);
            Timestamp fimTimestamp = Timestamp.valueOf(fimDateTime);
            List<Transferencia> transferenciasList = transferenciaRepository.findAllByDataTransferenciaBetween(
                    inicioTimestamp,
                    fimTimestamp);
            transferenciasPage = new PageImpl<>(transferenciasList, pageable, transferenciasList.size());
        } else {
            transferenciasPage = transferenciaRepository.findAll(pageable);
        }

        return transferenciasPage;
    }


    public Page<Transferencia> filtrarTransferenciasPorContaId(Long contaId, String nomeOperadorTransacao,
            LocalDate inicio, LocalDate fim, Pageable pageable) {

        if (nomeOperadorTransacao != null && inicio != null && fim != null) {
            LocalDateTime inicioDateTime = inicio.atStartOfDay();
            LocalDateTime fimDateTime = fim.atTime(23, 59, 59);
            Timestamp inicioTimestamp = Timestamp.valueOf(inicioDateTime);
            Timestamp fimTimestamp = Timestamp.valueOf(fimDateTime);
            return transferenciaRepository.findByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                    contaId, nomeOperadorTransacao, inicioTimestamp, fimTimestamp, pageable);
        } else if (nomeOperadorTransacao != null) {
            return transferenciaRepository.findByContaIdAndNomeOperadorTransacao(contaId, nomeOperadorTransacao, pageable);
        } else if (inicio != null && fim != null) {
            LocalDateTime inicioDateTime = inicio.atStartOfDay();
            LocalDateTime fimDateTime = fim.atTime(23, 59, 59);
            Timestamp inicioTimestamp = Timestamp.valueOf(inicioDateTime);
            Timestamp fimTimestamp = Timestamp.valueOf(fimDateTime);
            return transferenciaRepository.findByContaIdAndDataTransferenciaBetween(
                    contaId, inicioTimestamp, fimTimestamp, pageable);
        } else {
            return transferenciaRepository.findByContaId(contaId, pageable);
        }
    }

}

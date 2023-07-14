package br.com.banco.controller;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.transferencia.Transferencia;
import br.com.banco.transferencia.TransferenciaDTO;
import br.com.banco.transferencia.TransferenciaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
public class TransferenciaController {
    private final TransferenciaRepository repository;

    @Autowired
    public TransferenciaController(TransferenciaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transferencias/{id}")
    public ResponseEntity<List<TransferenciaDTO>> getTransferenciasByContaId(@PathVariable int id) {
        List<Transferencia> transferencias = repository.findByContaId(id);

        if (transferencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<TransferenciaDTO> transferenciaDTOs = transferencias.stream()
                    .map(TransferenciaDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(transferenciaDTOs);
        }
    }

    @GetMapping("/transferenciaas")
    public List<Transferencia> getTransferencias(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {

        Timestamp inicioTimestamp = Timestamp.valueOf(inicio);
        Timestamp fimTimestamp = Timestamp.valueOf(fim);

        return repository.findAllByDataTransferenciaBetween(inicioTimestamp, fimTimestamp);
    }

    @GetMapping("/transferencias")
    public ResponseEntity<Page<TransferenciaDTO>> getTransferencias(
        @RequestParam(required = false) String nomeOperadorTransacao,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
        Pageable pageable) {

    List<Transferencia> transferencias;

    if (nomeOperadorTransacao != null) {
        transferencias = repository.findByNomeOperadorTransacao(nomeOperadorTransacao);
    } else if (inicio != null && fim != null) {
        Timestamp inicioTimestamp = Timestamp.valueOf(inicio);
        Timestamp fimTimestamp = Timestamp.valueOf(fim);
        transferencias = repository.findAllByDataTransferenciaBetween(inicioTimestamp, fimTimestamp);
    } else {
        transferencias = repository.findAll();
    }

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Transferencia> transferenciasPaginadas;

        if (transferencias.size() < startItem) {
            transferenciasPaginadas = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, transferencias.size());
            transferenciasPaginadas = transferencias.subList(startItem, toIndex);
        }

        if (transferenciasPaginadas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<TransferenciaDTO> transferenciaDTOs = transferenciasPaginadas.stream()
                    .map(TransferenciaDTO::new)
                    .collect(Collectors.toList());

            Page<TransferenciaDTO> transferenciaPage = new PageImpl<>(transferenciaDTOs, pageable,
                    transferencias.size());

            return ResponseEntity.ok(transferenciaPage);
        }
    }

}
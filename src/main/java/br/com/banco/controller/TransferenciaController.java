package br.com.banco.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.response.TransferenciaRes;
import br.com.banco.dto.ContaInfoDTO;
import br.com.banco.dto.ContaSaldoDTO;

import br.com.banco.repository.TransferenciaRepository;

import br.com.banco.service.ContaService;
import br.com.banco.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

@RestController

@RequestMapping(produces = {"application/json"})
@Tag(name = "Banco")
public class TransferenciaController {

    private TransferenciaService transferenciaService;

    private ContaService contaService;

    public TransferenciaController(
            TransferenciaRepository transferenciaRepository,
            TransferenciaService transferenciaService,
            ContaService contaService) {
        this.transferenciaService = transferenciaService;
        this.contaService = contaService;
    }

    @Operation(summary = "Pesquisa por conta por id com queryParameters(Opcional)", method = "GET")
    @GetMapping("/transferencias/conta/{id}")
    public ResponseEntity<ContaSaldoDTO> getTransferenciasESaldoByContaId(
            @PathVariable Long id,
            @RequestParam(required = false) String nomeOperadorTransacao,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fim,
            Pageable pageable) {

        ContaSaldoDTO contaSaldoDTO = transferenciaService.getTransferenciasESaldoByContaId(id, nomeOperadorTransacao,
                inicio,
                fim, pageable);
        if (contaSaldoDTO.getTransferencias().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(contaSaldoDTO);
        }
    }

    @Operation(summary = "Retorna todas as transferencia com queryParameters(Opcional) ", method = "GET")
    @GetMapping("/transferencias")
    public ResponseEntity<TransferenciaRes> listTransferencias(
            @RequestParam(required = false) String nomeOperadorTransacao,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fim,
            Pageable pageable) {

        List<TransferenciaDTO> transferencias = transferenciaService.listTransferencias(nomeOperadorTransacao, inicio,
                fim, pageable);
        List<ContaInfoDTO> contasComTransferencias = contaService.listContas();

        TransferenciaRes response = new TransferenciaRes(transferencias, contasComTransferencias);

        return ResponseEntity.ok(response);
    }

}

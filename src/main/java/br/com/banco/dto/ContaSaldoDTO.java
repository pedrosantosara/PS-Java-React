package br.com.banco.dto;
import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ContaSaldoDTO {
    private Page<TransferenciaDTO> transferencias;
    private Float saldo;

    public ContaSaldoDTO(Page<TransferenciaDTO> transferencias, Float saldo) {
        this.transferencias = transferencias;
        this.saldo = saldo;
    }
}

package br.com.banco.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ContaInfoDTO {
    private Long contaId;
    private String nomeResponsavel;

    public ContaInfoDTO(Long contaId, String nomeResponsavel) {
        this.contaId = contaId;
        this.nomeResponsavel = nomeResponsavel;
    }
}


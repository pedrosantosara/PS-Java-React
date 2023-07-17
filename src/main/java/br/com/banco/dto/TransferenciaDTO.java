package br.com.banco.dto;

import java.sql.Timestamp;

import br.com.banco.entity.Transferencia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaDTO {
    private Long id;
    private Timestamp dataTransferencia;
    private Float valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private String nomeConta;
    private Long contaId;

    public TransferenciaDTO(Transferencia transferencia) {
        this.id = transferencia.getId();
        this.dataTransferencia = transferencia.getDataTransferencia();
        this.valor = transferencia.getValor();
        this.tipo = transferencia.getTipo();
        this.nomeOperadorTransacao = transferencia.getNomeOperadorTransacao();
        this.nomeConta = transferencia.getConta().getNomeResponsavel();
        this.contaId = transferencia.getConta().getId();
    }
}

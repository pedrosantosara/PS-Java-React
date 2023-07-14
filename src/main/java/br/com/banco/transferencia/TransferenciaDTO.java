package br.com.banco.transferencia;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TransferenciaDTO {
    private int id;
    private Timestamp dataTransferencia;
    private Float valor;
    private String tipo;
    private String nomeOperadorTransacao;

    public TransferenciaDTO(Transferencia transferencia) {
        this.id = transferencia.getId();
        this.dataTransferencia = transferencia.getDataTransferencia();
        this.valor = transferencia.getValor();
        this.tipo = transferencia.getTipo();
        this.nomeOperadorTransacao = transferencia.getNomeOperadorTransacao();
    }
}

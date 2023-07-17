package br.com.banco.response;

import br.com.banco.dto.ContaInfoDTO;
import br.com.banco.dto.TransferenciaDTO;


import java.util.List;

public class TransferenciaRes {
    private List<TransferenciaDTO> transferencias;
    private List<ContaInfoDTO> contas;

    public TransferenciaRes(List<TransferenciaDTO> transferencias, List<ContaInfoDTO> contas) {
        this.transferencias = transferencias;
        this.contas = contas;
    }

    public List<TransferenciaDTO> getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(List<TransferenciaDTO> transferencias) {
        this.transferencias = transferencias;
    }

    public List<ContaInfoDTO> getContas() {
        return contas;
    }

    public void setContas(List<ContaInfoDTO> contas) {
        this.contas = contas;
    }
}

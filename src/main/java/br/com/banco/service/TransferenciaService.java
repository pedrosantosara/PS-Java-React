package br.com.banco.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.banco.dto.ContaSaldoDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entity.Transferencia;

import br.com.banco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

  private TransferenciaRepository transferenciaRepository;
 
    @Autowired
    private ParamsService paramsService;

    
  public TransferenciaService(TransferenciaRepository transferenciaRepository) {
    this.transferenciaRepository = transferenciaRepository;
  }


 public List<TransferenciaDTO> listTransferencias(String nomeOperadorTransacao, LocalDate inicio, LocalDate fim, Pageable pageable) {
    Page<Transferencia> transferenciasPage = paramsService.filtrarTransferencias(nomeOperadorTransacao, inicio, fim, pageable);
    return transferenciasPage.getContent().stream().map(TransferenciaDTO::new).collect(Collectors.toList());
}


  public ContaSaldoDTO getTransferenciasESaldoByContaId(Long contaId,String nomeOperadorTransacao, LocalDate inicio, LocalDate fim, Pageable pageable) {
    Page<Transferencia> transferencias = paramsService.filtrarTransferenciasPorContaId(contaId, nomeOperadorTransacao, inicio, fim, pageable);
    Page<TransferenciaDTO> transferenciasDTO = transferencias.map(TransferenciaDTO::new);

    Float saldo = calcularSaldo(transferenciasDTO.getContent());
    return new ContaSaldoDTO(transferenciasDTO, saldo); 
}

 public List<Transferencia> listTransferenciasByContaIdAndNome(Long contaId, String nomeResponsavel) {
        return transferenciaRepository.findByContaIdAndContaNomeResponsavel(contaId, nomeResponsavel);
    }




  public Float calcularSaldo(List<TransferenciaDTO> transferencias) {
    Float saldo = 0f;

    for (TransferenciaDTO transferencia : transferencias) {
        if (transferencia.getTipo().equalsIgnoreCase("DEPOSITO")) {
            saldo += transferencia.getValor();
        } else if (transferencia.getTipo().equalsIgnoreCase("SAQUE")) {
            saldo += transferencia.getValor();
        } else if (transferencia.getTipo().equalsIgnoreCase("TRANSFERENCIA")) {
            saldo += transferencia.getValor();
        }
    }

    return saldo;
}
}

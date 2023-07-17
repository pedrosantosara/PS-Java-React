package br.com.banco.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.com.banco.dto.ContaInfoDTO;
import br.com.banco.entity.Conta;

import br.com.banco.repository.ContaRepository;

@Service
public class ContaService {

    private ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Long getContaIdByNomeResponsavel(String nomeResponsavel) {
        Optional<Conta> contaOptional = contaRepository.findByNomeResponsavel(nomeResponsavel);
        return contaOptional.map(Conta::getId).orElse(null);
    }

    public List<ContaInfoDTO> listContas() {
        List<Conta> contas = contaRepository.findAll();
        return contas.stream()
                .map(conta -> new ContaInfoDTO(conta.getId(), conta.getNomeResponsavel()))
                .collect(Collectors.toList());
    }

}

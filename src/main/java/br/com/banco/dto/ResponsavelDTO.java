package br.com.banco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResponsavelDTO {
  private Long id;
    private String nomeResponsavel;

    public ResponsavelDTO(Long id, String nomeResponsavel) {
        this.id = id;
        this.nomeResponsavel = nomeResponsavel;
    }
  
}

package br.com.banco.controller;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;



@RestController
public class ContaController {
  
  @PersistenceContext
  private EntityManager entityManager;

  @GetMapping("/conta")
    public List<Object[]> getMeusDados() {
        Query query = entityManager.createNativeQuery("SELECT * FROM conta");
        List<Object[]> rows = query.getResultList();
        return rows;
    }
}

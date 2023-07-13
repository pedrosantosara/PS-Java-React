package br.com.banco.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@LiquibaseDataSource
@RestController
public class TransferenciaController {
   @PersistenceContext
  private EntityManager entityManager;

  @GetMapping("/transferencia")
    public List<Object[]> getMeusDados() {
        Query query = entityManager.createNativeQuery("SELECT * FROM transferencia");
        List<Object[]> rows = query.getResultList();
        return rows;
    }
}

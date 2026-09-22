package br.com.locadora.repository;

import br.com.locadora.model.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {}

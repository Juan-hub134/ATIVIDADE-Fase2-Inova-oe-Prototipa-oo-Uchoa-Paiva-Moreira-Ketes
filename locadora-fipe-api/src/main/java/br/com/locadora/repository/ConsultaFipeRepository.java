package br.com.locadora.repository;

import br.com.locadora.model.ConsultaFipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaFipeRepository extends JpaRepository<ConsultaFipe, Long> {
    java.util.List<ConsultaFipe> findByVeiculoId(Long veiculoId);
}

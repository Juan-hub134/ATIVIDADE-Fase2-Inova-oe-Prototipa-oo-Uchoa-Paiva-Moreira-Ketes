package br.com.locadora.controller;

import br.com.locadora.dto.ConsultaFipeRequest;
import br.com.locadora.exception.RecursoNaoEncontradoException;
import br.com.locadora.model.ConsultaFipe;
import br.com.locadora.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consultas-fipe")
public class ConsultaFipeController {
    private final ConsultaFipeRepository consultas;
    private final VeiculoRepository veiculos;

    public ConsultaFipeController(ConsultaFipeRepository consultas, VeiculoRepository veiculos) {
        this.consultas = consultas; this.veiculos = veiculos;
    }

    @GetMapping public List<ConsultaFipe> listar() { return consultas.findAll(); }

    @GetMapping("/{id}")
    public ConsultaFipe buscar(@PathVariable Long id) {
        return consultas.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Consulta FIPE não encontrada"));
    }

    @GetMapping("/veiculo/{veiculoId}")
    public List<ConsultaFipe> historico(@PathVariable Long veiculoId) {
        return consultas.findByVeiculoId(veiculoId);
    }

    @PostMapping
    public ResponseEntity<ConsultaFipe> criar(@Valid @RequestBody ConsultaFipeRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultas.save(preencher(new ConsultaFipe(), r)));
    }

    @PutMapping("/{id}")
    public ConsultaFipe atualizar(@PathVariable Long id, @Valid @RequestBody ConsultaFipeRequest r) {
        return consultas.save(preencher(buscar(id), r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        consultas.delete(buscar(id));
        return ResponseEntity.noContent().build();
    }

    private ConsultaFipe preencher(ConsultaFipe c, ConsultaFipeRequest r) {
        c.setVeiculo(veiculos.findById(r.idVeiculo())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado")));
        c.setCodigoFipe(r.codigoFipe());
        c.setValor(r.valor());
        c.setMesReferencia(r.mesReferencia());
        c.setDataConsulta(r.dataConsulta());
        return c;
    }
}

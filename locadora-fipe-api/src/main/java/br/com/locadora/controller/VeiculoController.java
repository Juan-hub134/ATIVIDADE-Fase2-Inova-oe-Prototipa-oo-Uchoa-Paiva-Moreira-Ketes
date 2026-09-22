package br.com.locadora.controller;

import br.com.locadora.exception.RecursoNaoEncontradoException;
import br.com.locadora.model.Veiculo;
import br.com.locadora.repository.VeiculoRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoRepository repository;
    public VeiculoController(VeiculoRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Veiculo> listar() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Veiculo buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Veiculo não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Veiculo> criar(@Valid @RequestBody Veiculo veiculo) {
        veiculo.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(veiculo));
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @Valid @RequestBody Veiculo dados) {
        Veiculo atual = buscar(id);
        atual.setPlaca(dados.getPlaca());
        atual.setMarca(dados.getMarca());
        atual.setModelo(dados.getModelo());
        atual.setTipo(dados.getTipo());
        return repository.save(atual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Veiculo atual = buscar(id);
        repository.delete(atual);
        return ResponseEntity.noContent().build();
    }
}

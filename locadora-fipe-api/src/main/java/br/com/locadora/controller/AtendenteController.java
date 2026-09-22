package br.com.locadora.controller;

import br.com.locadora.exception.RecursoNaoEncontradoException;
import br.com.locadora.model.Atendente;
import br.com.locadora.repository.AtendenteRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/atendentes")
public class AtendenteController {
    private final AtendenteRepository repository;
    public AtendenteController(AtendenteRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Atendente> listar() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Atendente buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Atendente não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Atendente> criar(@Valid @RequestBody Atendente atendente) {
        atendente.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(atendente));
    }

    @PutMapping("/{id}")
    public Atendente atualizar(@PathVariable Long id, @Valid @RequestBody Atendente dados) {
        Atendente atual = buscar(id);
        atual.setCpf(dados.getCpf());
        atual.setNome(dados.getNome());
        atual.setSobrenome(dados.getSobrenome());
        atual.setEndereco(dados.getEndereco());
        atual.setEmail(dados.getEmail());
        return repository.save(atual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Atendente atual = buscar(id);
        repository.delete(atual);
        return ResponseEntity.noContent().build();
    }
}

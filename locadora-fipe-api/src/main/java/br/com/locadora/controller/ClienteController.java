package br.com.locadora.controller;

import br.com.locadora.exception.RecursoNaoEncontradoException;
import br.com.locadora.model.Cliente;
import br.com.locadora.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteRepository repository;
    public ClienteController(ClienteRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Cliente> listar() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
        cliente.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @Valid @RequestBody Cliente dados) {
        Cliente atual = buscar(id);
        atual.setCpf(dados.getCpf());
        atual.setNome(dados.getNome());
        atual.setSobrenome(dados.getSobrenome());
        atual.setEndereco(dados.getEndereco());
        atual.setDadosBancarios(dados.getDadosBancarios());
        atual.setEmail(dados.getEmail());
        return repository.save(atual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Cliente atual = buscar(id);
        repository.delete(atual);
        return ResponseEntity.noContent().build();
    }
}

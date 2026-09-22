package br.com.locadora.controller;

import br.com.locadora.dto.ContratoRequest;
import br.com.locadora.exception.RecursoNaoEncontradoException;
import br.com.locadora.model.*;
import br.com.locadora.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {
    private final ContratoRepository contratos;
    private final ClienteRepository clientes;
    private final VeiculoRepository veiculos;
    private final AtendenteRepository atendentes;

    public ContratoController(ContratoRepository contratos, ClienteRepository clientes,
                              VeiculoRepository veiculos, AtendenteRepository atendentes) {
        this.contratos = contratos; this.clientes = clientes;
        this.veiculos = veiculos; this.atendentes = atendentes;
    }

    @GetMapping public List<Contrato> listar() { return contratos.findAll(); }

    @GetMapping("/{id}")
    public Contrato buscar(@PathVariable Long id) {
        return contratos.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Contrato> criar(@Valid @RequestBody ContratoRequest r) {
        Contrato c = preencher(new Contrato(), r);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratos.save(c));
    }

    @PutMapping("/{id}")
    public Contrato atualizar(@PathVariable Long id, @Valid @RequestBody ContratoRequest r) {
        return contratos.save(preencher(buscar(id), r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        contratos.delete(buscar(id));
        return ResponseEntity.noContent().build();
    }

    private Contrato preencher(Contrato c, ContratoRequest r) {
        if (r.fimVigencia().isBefore(r.inicioVigencia()))
            throw new IllegalArgumentException("fimVigencia não pode ser anterior a inicioVigencia");
        c.setData(r.data());
        c.setTipoPagamento(r.tipoPagamento());
        c.setInicioVigencia(r.inicioVigencia());
        c.setFimVigencia(r.fimVigencia());
        c.setCliente(clientes.findById(r.idCliente()).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado")));
        c.setVeiculo(veiculos.findById(r.idVeiculo()).orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado")));
        c.setAtendente(atendentes.findById(r.idAtendente()).orElseThrow(() -> new RecursoNaoEncontradoException("Atendente não encontrado")));
        return c;
    }
}

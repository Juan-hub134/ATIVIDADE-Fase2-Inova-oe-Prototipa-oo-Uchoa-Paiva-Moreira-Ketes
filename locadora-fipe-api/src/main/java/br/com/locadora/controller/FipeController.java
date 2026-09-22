package br.com.locadora.controller;

import br.com.locadora.service.FipeService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fipe")
public class FipeController {
    private final FipeService service;
    public FipeController(FipeService service) { this.service = service; }

    @GetMapping("/{tipo}/marcas")
    public JsonNode marcas(@PathVariable String tipo) {
        return service.marcas(tipo);
    }

    @GetMapping("/{tipo}/marcas/{marca}/modelos")
    public JsonNode modelos(@PathVariable String tipo, @PathVariable String marca) {
        return service.modelos(tipo, marca);
    }

    @GetMapping("/{tipo}/marcas/{marca}/modelos/{modelo}/anos")
    public JsonNode anos(@PathVariable String tipo, @PathVariable String marca, @PathVariable String modelo) {
        return service.anos(tipo, marca, modelo);
    }

    @GetMapping("/{tipo}/marcas/{marca}/modelos/{modelo}/anos/{ano}")
    public JsonNode veiculo(@PathVariable String tipo, @PathVariable String marca,
                            @PathVariable String modelo, @PathVariable String ano) {
        return service.veiculo(tipo, marca, modelo, ano);
    }
}

package br.com.locadora.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FipeService {
    private final RestClient client;

    public FipeService(RestClient.Builder builder, @Value("${fipe.base-url}") String baseUrl) {
        this.client = builder.baseUrl(baseUrl).build();
    }

    public JsonNode marcas(String tipo) {
        return get("/" + validarTipo(tipo) + "/marcas");
    }

    public JsonNode modelos(String tipo, String marca) {
        return get("/" + validarTipo(tipo) + "/marcas/" + marca + "/modelos");
    }

    public JsonNode anos(String tipo, String marca, String modelo) {
        return get("/" + validarTipo(tipo) + "/marcas/" + marca + "/modelos/" + modelo + "/anos");
    }

    public JsonNode veiculo(String tipo, String marca, String modelo, String ano) {
        return get("/" + validarTipo(tipo) + "/marcas/" + marca + "/modelos/" + modelo + "/anos/" + ano);
    }

    private JsonNode get(String uri) {
        return client.get().uri(uri).retrieve().body(JsonNode.class);
    }

    private String validarTipo(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "carros", "motos", "caminhoes" -> tipo.toLowerCase();
            default -> throw new IllegalArgumentException("tipo deve ser carros, motos ou caminhoes");
        };
    }
}

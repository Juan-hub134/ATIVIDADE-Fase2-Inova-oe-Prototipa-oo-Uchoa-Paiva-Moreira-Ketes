package br.com.locadora.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ContratoRequest(
        @NotNull LocalDate data,
        @NotBlank String tipoPagamento,
        @NotNull LocalDate inicioVigencia,
        @NotNull LocalDate fimVigencia,
        @NotNull Long idCliente,
        @NotNull Long idVeiculo,
        @NotNull Long idAtendente
) {}

package br.com.locadora.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ConsultaFipeRequest(
        @NotNull Long idVeiculo,
        @NotBlank String codigoFipe,
        @NotNull @PositiveOrZero BigDecimal valor,
        String mesReferencia,
        @NotNull LocalDate dataConsulta
) {}

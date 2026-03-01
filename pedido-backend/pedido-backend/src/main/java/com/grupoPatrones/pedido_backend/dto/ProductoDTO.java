package com.tugrupo.pedidobackend.dto;

import java.math.BigDecimal;

// Usando Record de Java (ideal para DTOs inmutables)
public record ProductoDTO(
    Long id,
    String nombre,
    String descripcion,
    BigDecimal precio,
    Integer stockDisponible,
    Boolean activo
) {}
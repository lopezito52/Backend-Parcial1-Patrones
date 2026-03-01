package com.tugrupo.pedidobackend.service;

import com.tugrupo.pedidobackend.dto.request.ProductoRequestDTO;
import com.tugrupo.pedidobackend.dto.response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoResponseDTO> obtenerTodosLosProductos();
    List<ProductoResponseDTO> obtenerProductosActivos();
    ProductoResponseDTO obtenerProductoPorId(Long id);
    ProductoResponseDTO crearProducto(ProductoRequestDTO productoRequest);
    ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO productoRequest);
    void eliminarProducto(Long id);
    boolean existeProductoPorNombre(String nombre);
}
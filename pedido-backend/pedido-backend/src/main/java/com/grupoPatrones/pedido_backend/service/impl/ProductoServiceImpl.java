package com.tugrupo.pedidobackend.service.impl;

import com.tugrupo.pedidobackend.dto.request.ProductoRequestDTO;
import com.tugrupo.pedidobackend.dto.response.ProductoResponseDTO;
import com.tugrupo.pedidobackend.model.Producto;
import com.tugrupo.pedidobackend.repository.ProductoRepository;
import com.tugrupo.pedidobackend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerProductosActivos() {
        return productoRepository.findByActivoTrue()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return convertirAResponseDTO(producto);
    }

    @Override
    public ProductoResponseDTO crearProducto(ProductoRequestDTO requestDTO) {
        // Validar que no exista un producto con el mismo nombre
        if (productoRepository.existsByNombre(requestDTO.getNombre())) {
            throw new RuntimeException("Ya existe un producto con el nombre: " + requestDTO.getNombre());
        }

        Producto producto = new Producto();
        producto.setNombre(requestDTO.getNombre());
        producto.setDescripcion(requestDTO.getDescripcion());
        producto.setPrecio(requestDTO.getPrecio());
        producto.setStockDisponible(requestDTO.getStockDisponible() != null ? 
                requestDTO.getStockDisponible() : 0);
        producto.setActivo(requestDTO.getActivo() != null ? requestDTO.getActivo() : true);

        Producto productoGuardado = productoRepository.save(producto);
        return convertirAResponseDTO(productoGuardado);
    }

    @Override
    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO requestDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        // Actualizar campos
        producto.setNombre(requestDTO.getNombre());
        producto.setDescripcion(requestDTO.getDescripcion());
        producto.setPrecio(requestDTO.getPrecio());
        if (requestDTO.getStockDisponible() != null) {
            producto.setStockDisponible(requestDTO.getStockDisponible());
        }
        if (requestDTO.getActivo() != null) {
            producto.setActivo(requestDTO.getActivo());
        }

        Producto productoActualizado = productoRepository.save(producto);
        return convertirAResponseDTO(productoActualizado);
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    @Override
    public boolean existeProductoPorNombre(String nombre) {
        return productoRepository.existsByNombre(nombre);
    }

    // Método privado para convertir Entity a ResponseDTO
    private ProductoResponseDTO convertirAResponseDTO(Producto producto) {
        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stockDisponible(producto.getStockDisponible())
                .activo(producto.getActivo())
                .build();
    }
}
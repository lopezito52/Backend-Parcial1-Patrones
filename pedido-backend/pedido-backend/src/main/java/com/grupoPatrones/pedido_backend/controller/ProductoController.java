package com.tugrupo.pedidobackend.controller;

import com.tugrupo.pedidobackend.dto.request.ProductoRequestDTO;
import com.tugrupo.pedidobackend.dto.response.ProductoResponseDTO;
import com.tugrupo.pedidobackend.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // Permite peticiones del frontend
public class ProductoController {

    private final ProductoService productoService;

    // GET /api/productos - Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodosLosProductos() {
        List<ProductoResponseDTO> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    // GET /api/productos/activos - Obtener solo productos activos
    @GetMapping("/activos")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductosActivos() {
        List<ProductoResponseDTO> productos = productoService.obtenerProductosActivos();
        return ResponseEntity.ok(productos);
    }

    // GET /api/productos/{id} - Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(@PathVariable Long id) {
        ProductoResponseDTO producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    // POST /api/productos - Crear nuevo producto
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoRequestDTO productoRequest) {
        ProductoResponseDTO nuevoProducto = productoService.crearProducto(productoRequest);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // PUT /api/productos/{id} - Actualizar producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO productoRequest) {
        ProductoResponseDTO productoActualizado = productoService.actualizarProducto(id, productoRequest);
        return ResponseEntity.ok(productoActualizado);
    }

    // DELETE /api/productos/{id} - Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/productos/existe/{nombre} - Verificar si existe producto por nombre
    @GetMapping("/existe/{nombre}")
    public ResponseEntity<Boolean> existeProductoPorNombre(@PathVariable String nombre) {
        boolean existe = productoService.existeProductoPorNombre(nombre);
        return ResponseEntity.ok(existe);
    }
}
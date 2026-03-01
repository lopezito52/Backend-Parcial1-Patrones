package com.tugrupo.pedidobackend.repository;

import com.tugrupo.pedidobackend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Métodos de consulta personalizados
    List<Producto> findByActivoTrue();
    
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
    boolean existsByNombre(String nombre);
}
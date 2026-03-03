package com.grupoPatrones.pedido_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.grupoPatrones.pedido_backend",
        "com.tugrupo.pedidobackend"
})
@EnableJpaRepositories(basePackages = "com.tugrupo.pedidobackend.repository")
@EntityScan(basePackages = "com.tugrupo.pedidobackend.model")
public class PedidoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidoBackendApplication.class, args);
    }
}

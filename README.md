# 🛒 Pedido Backend API

API REST para sistema de gestión de pedidos (productos). Desarrollada con **Spring Boot** y **Maven**.

---

## 📌 Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/productos` | Obtener todos los productos |
| `GET` | `/api/productos/activos` | Obtener solo productos activos |
| `GET` | `/api/productos/{id}` | Obtener un producto por ID |
| `POST` | `/api/productos` | Crear un nuevo producto |
| `PUT` | `/api/productos/{id}` | Actualizar un producto existente |
| `DELETE` | `/api/productos/{id}` | Eliminar un producto |

---

## 🚀 Cómo ejecutar localmente

### Requisitos

- Java 17
- Maven
- Docker _(opcional, para PostgreSQL)_

### Pasos

**1. Clonar el repositorio**

```bash
git clone <tu-repo-url>
cd pedido-backend
```

**2. Ejecutar con Maven**

```bash
mvn spring-boot:run
```

**3. Probar que funciona**

```bash
curl http://localhost:8080/api/productos
```

## Integrantes

- Samuel Acero Garcia
- Deivid Nicolas Urrea Lara
- Samuel Esteban López Huertas

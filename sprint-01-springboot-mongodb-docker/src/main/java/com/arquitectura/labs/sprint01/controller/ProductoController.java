package com.arquitectura.labs.sprint01.controller;

import com.arquitectura.labs.sprint01.dto.ProductoRequestDTO;
import com.arquitectura.labs.sprint01.dto.ProductoResponseDTO;
import com.arquitectura.labs.sprint01.model.Producto;
import com.arquitectura.labs.sprint01.service.impl.ProductoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/producto/producto-service")
public class ProductoController {


    private final ProductoServiceImpl productoService;

    public ProductoController(ProductoServiceImpl productoService) {
        this.productoService = productoService;
    }


    /* EndPoint crear producto */
    @PostMapping("/crear")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO response = new ProductoResponseDTO();

        try {
            Producto producto = new Producto();
            producto.setNombre(request.getNombre().trim());
            producto.setDescripcion(request.getDescripcion().trim());
            producto.setPrecio(request.getPrecio());

            Producto productoCreado = productoService.crear(producto);

            return Optional.ofNullable(productoCreado)
                    .filter(p -> p.getId() != null)
                    .map(p -> {
                        response.setMensaje("El producto se creó correctamente");
                        response.setProducto(p);
                        response.setFechaEjecucion(new Date());
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                    })
                    .orElseGet(() -> {
                        response.setMensaje("No se pudo crear el producto");
                        response.setFechaEjecucion(new Date());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                    });

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            response.setMensaje("Error desconocido al crear producto");
            response.setFechaEjecucion(new Date());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /* EndPoint actualiza producto */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable("id") String id,
            @RequestBody ProductoRequestDTO request) {

        ProductoResponseDTO response = new ProductoResponseDTO();

        try {
            Optional<Producto> productoOptional = productoService.obtenerPorId(id);

            if (productoOptional.isEmpty()) {
                response.setMensaje("Producto no encontrado con ID: " + id);
                response.setFechaEjecucion(new Date());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            Producto producto = productoOptional.get();
            producto.setNombre(request.getNombre().trim());
            producto.setDescripcion(request.getDescripcion().trim());
            producto.setPrecio(request.getPrecio());

            Producto productoActualizado = productoService.actualizar(producto);

            return Optional.ofNullable(productoActualizado)
                    .filter(p -> p.getId() != null)
                    .map(p -> {
                        response.setMensaje("El producto se actualizó correctamente");
                        response.setProducto(p);
                        response.setFechaEjecucion(new Date());
                        return ResponseEntity.status(HttpStatus.OK).body(response);
                    })
                    .orElseGet(() -> {
                        response.setMensaje("No se pudo actualizar el producto");
                        response.setFechaEjecucion(new Date());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                    });

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            response.setMensaje("Error desconocido al actualizar producto");
            response.setFechaEjecucion(new Date());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /* EndPoint lista producto */
    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.listar();
    }


    /* EndPoint eliminar producto por ID */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ProductoResponseDTO> eliminar(@PathVariable("id") String id) {
        ProductoResponseDTO response = new ProductoResponseDTO();

        try {
            boolean eliminado = productoService.eliminar(id);

            if (eliminado) {
                response.setMensaje("Producto eliminado correctamente");
                response.setFechaEjecucion(new Date());
                return ResponseEntity.ok(response);
            } else {
                response.setMensaje("No se encontró producto con ID: " + id);
                response.setFechaEjecucion(new Date());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            response.setMensaje("Error al eliminar producto");
            response.setFechaEjecucion(new Date());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

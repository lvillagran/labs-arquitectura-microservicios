package com.arquitectura.labs.sprint01.service.impl;

import com.arquitectura.labs.sprint01.model.Producto;
import com.arquitectura.labs.sprint01.repository.ProductoRepository;
import com.arquitectura.labs.sprint01.service.IProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> obtenerPorId(String id) {
        return productoRepository.findById(id);
    }

    public Producto actualizar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public boolean eliminar(String id) {
        Optional<Producto> productoOptional = obtenerPorId(id);
        if (productoOptional.isPresent()) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

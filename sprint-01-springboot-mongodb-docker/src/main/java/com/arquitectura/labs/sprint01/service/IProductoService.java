package com.arquitectura.labs.sprint01.service;

import com.arquitectura.labs.sprint01.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listar();
    Producto crear(Producto producto);
    public Optional<Producto> obtenerPorId(String id);
    public Producto actualizar(Producto producto);
    boolean eliminar(String id);
}

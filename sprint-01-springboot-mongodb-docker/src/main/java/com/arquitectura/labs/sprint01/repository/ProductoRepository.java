package com.arquitectura.labs.sprint01.repository;

import com.arquitectura.labs.sprint01.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, String> {
}
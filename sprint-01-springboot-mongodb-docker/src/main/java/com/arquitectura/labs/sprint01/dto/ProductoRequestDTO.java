package com.arquitectura.labs.sprint01.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequestDTO extends RequestDTO {

    private String nombre;
    private String descripcion;
    private double precio;
}
